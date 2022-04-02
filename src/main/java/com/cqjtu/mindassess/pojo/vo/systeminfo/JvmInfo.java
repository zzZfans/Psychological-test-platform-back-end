package com.cqjtu.mindassess.pojo.vo.systeminfo;

import lombok.Data;

/**
 * @author Zfans
 */
@Data
public class JvmInfo {
    private String jvmMaxMemory;
    private String jvmUsableMemory;
    private String jvmTotalMemory;
    private String jvmUsedMemory;
    private String jvmFreeMemory;
    private String jvmMemoryUsedRate;
}
