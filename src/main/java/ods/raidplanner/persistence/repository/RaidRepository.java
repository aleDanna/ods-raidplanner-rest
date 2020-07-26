package ods.raidplanner.persistence.repository;

import ods.raidplanner.persistence.model.Raid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RaidRepository extends CrudRepository<Raid, Long>, JpaSpecificationExecutor {

    private static Specification<Raid> byStartDate(LocalDateTime startDateFilter) {
        return (root, query, cb) -> startDateFilter == null ? null : cb.greaterThanOrEqualTo(root.get("startDate"), startDateFilter);
    }

    private static Specification<Raid> byEndDate(LocalDateTime endDateFilter) {
        return (root, query, cb) -> endDateFilter == null ? null : cb.lessThanOrEqualTo(root.get("startDate"), endDateFilter);
    }

    private static Specification<Raid> byGroupIds(List<Long> groupIds) {
        return (root, query, cb) -> groupIds == null ? null : root.join("raidGroup").get("id").in(groupIds);
    }

    private static Specification<Raid> byMaxRank(Integer maxRankFilter) {
        return (root, query, cb) -> maxRankFilter == null ? null : cb.lessThanOrEqualTo(root.join("raidGroup").get("rank"), maxRankFilter);
    }

    static Specification<Raid> getByFilters(LocalDateTime startDateFilter,
                                           LocalDateTime endDateFilter,
                                           List<Long> groupIdsFilter,
                                           Integer maxRankFilter) {
        return Specification
                .where(byStartDate(startDateFilter))
                .and(byEndDate(endDateFilter))
                .and(byGroupIds(groupIdsFilter))
                .and(byMaxRank(maxRankFilter));
    }
}
