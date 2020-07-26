package ods.raidplanner.controller;

import ods.raidplanner.dto.*;
import ods.raidplanner.exceptions.NotFoundException;
import ods.raidplanner.exceptions.ODSException;
import ods.raidplanner.service.RaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/raids")
public class RaidController {

    @Autowired
    private RaidService raidService;

    @GetMapping(value = "/{raidId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getRaid(@PathVariable Long raidId) {
        try {
            RaidDTO result = raidService.getRaid(raidId);
            return ResponseEntity.ok(result);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/raidGroups", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getRaidGroups() {
        try {
            List<RaidGroupDTO> result = raidService.getRaidGroups();
            return ResponseEntity.ok(result);
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/schedule", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity scheduleEvent(@RequestBody EventDTO event) {
        try {
            RaidDTO result = raidService.saveRaid(event.getRaid(), event.isRecurrent());
            return ResponseEntity.ok(result);
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteEvent/{raidId}")
    public ResponseEntity deleteEvent(@PathVariable Long raidId) {
        try {
            raidService.deleteEvent(raidId);
            return ResponseEntity.ok().build();
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/updateSubscriptions", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity scheduleEvent(@RequestBody List<SubscriptionDTO> subscriptions) {
        try {
            raidService.updateSubscriptions(subscriptions);
            return ResponseEntity.ok().build();
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/getRaidsByFilter", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity getRaidsByFilter(@RequestBody RaidSearchFilterDTO filters) {
        try {
            List<RaidDTO> result = raidService.getRaidsByFilter(filters);
            return ResponseEntity.ok(result);
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/subscribe/{raidId}/{characterId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity subscribeToRaid(@PathVariable Long raidId, @PathVariable Long characterId) {
        try {
            SubscriptionDTO result = raidService.subscribe(raidId, characterId);
            return ResponseEntity.ok(result);
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/unsubscribe/{raidId}/{userId}")
    public ResponseEntity unsubscribe(@PathVariable Long raidId, @PathVariable Long userId) {
        try {
            raidService.unsubscribe(raidId, userId);
            return ResponseEntity.ok().build();
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/subscribedRaids/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getSubscribedRaids(@PathVariable Long userId) {
        try {
            List<RaidDTO> result = raidService.getSubscribedRaids(userId);
            return ResponseEntity.ok(result);
        } catch (ODSException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
