package com.chen.ddd.core.common.objectmapper;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public interface SourceToTargetMapper<Source, Target> extends Mapper{

    /**
     * source -> target
     *
     * @param source source
     * @return target
     */
    Target sourceToTarget(Source source);

    /**
     * source list -> target list
     *
     * @param sourceList source list
     * @return target list
     */
    default List<Target> sourceListToTargetList(List<? extends Source> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }

        return sourceList.stream()
                .map(this::sourceToTarget)
                .collect(Collectors.toList());
    }

    /**
     * source set -> target list
     *
     * @param sourceSet source set
     * @return target set
     */
    default List<Target> sourceSetToTargetList(Set<? extends Source> sourceSet) {
        if (CollectionUtils.isEmpty(sourceSet)) {
            return Collections.emptyList();
        }

        return sourceSet.stream()
                .map(this::sourceToTarget)
                .collect(Collectors.toList());
    }


    /**
     * source list -> target set
     *
     * @param sourceList source list
     * @return target set
     */
    default Set<Target> sourceListToTargetSet(List<? extends Source> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptySet();
        }

        return sourceList.stream()
                .map(this::sourceToTarget)
                .collect(Collectors.toSet());
    }
}
