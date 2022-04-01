package com.cqjtu.mindassess.pojo.vo.sysinfo;

import lombok.Data;

@Data
public class SysJvmInfo {
    private String jvmMaxMemory;
    private String jvmUsableMemory;
    private String jvmTotalMemory;
    private String jvmUsedMemory;
    private String jvmFreeMemory;
    private String jvmMemoryUsedRate;
}
