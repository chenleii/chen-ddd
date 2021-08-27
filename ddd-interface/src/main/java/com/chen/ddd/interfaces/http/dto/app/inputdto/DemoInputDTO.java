package com.chen.ddd.interfaces.http.dto.app.inputdto;

import com.chen.ddd.interfaces.http.dto.DTO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cl
 * @version 1.0
 * @since 2021/6/30 16:18
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("示例输入数据")
public class DemoInputDTO implements DTO {

}
