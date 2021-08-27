package com.chen.ddd.interfaces.http.dto.app.outputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 15:29
 */

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@ApiModel("示例输出数据")
public class DemoOutputDTO implements DTO {

    private static final long serialVersionUID = 1L;

}
