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
public interface TargetToSourceMapper<Source, Target> extends Mapper {


    /**
     * target -> source
     *
     * @param target target
     * @return source
     */
    Source targetToSource(Target target);

    /**
     * target list -> source list
     *
     * @param targetList target list
     * @return source list
     */
    default List<Source> targetListToSourceList(List<? extends Target> targetList) {
        if (CollectionUtils.isEmpty(targetList)) {
            return Collections.emptyList();
        }

        return targetList.stream()
                .map(this::targetToSource)
                .collect(Collectors.toList());
    }
    /**
     * target set -> source list
     *
     * @param targetSet target set
     * @return source list
     */
    default List<Source> targetSetToSourceList(Set<? extends Target> targetSet) {
        if (CollectionUtils.isEmpty(targetSet)) {
            return Collections.emptyList();
        }

        return targetSet.stream()
                .map(this::targetToSource)
                .collect(Collectors.toList());
    }

    /**
     * target list -> source set
     *
     * @param targetList target list
     * @return source set
     */
    default Set<Source> targetListToSourceSet(List<? extends Target> targetList) {
        if (CollectionUtils.isEmpty(targetList)) {
            return Collections.emptySet();
        }

        return targetList.stream()
                .map(this::targetToSource)
                .collect(Collectors.toSet());
    }
}
