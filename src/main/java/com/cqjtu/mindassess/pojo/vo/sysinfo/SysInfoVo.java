package com.cqjtu.mindassess.pojo.vo.sysinfo;

import lombok.Data;

@Data
public class SysInfoVo {
    private SysJvmInfo jvmInfo;
    private SysJavaInfo javaRuntimeInfo;
    private SysOsInfo osInfo;
}
