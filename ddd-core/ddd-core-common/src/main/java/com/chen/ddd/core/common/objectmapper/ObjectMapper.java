package com.chen.ddd.core.common.objectmapper;

/**
 * @author cl
 * @version 1.0
 * @since 2020/10/29
 */
public interface ObjectMapper<Source, Target>
        extends SourceToTargetMapper<Source, Target>, TargetToSourceMapper<Source, Target>, Mapper {

}
