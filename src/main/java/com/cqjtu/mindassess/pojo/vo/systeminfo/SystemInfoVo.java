package com.cqjtu.mindassess.pojo.vo.systeminfo;

import lombok.Data;

/**
 * @author Zfans
 */
@Data
public class SystemInfoVo {
    private JvmInfo jvmInfo;
    private JavaInfo javaRuntimeInfo;
    private OsInfo osInfo;
}
