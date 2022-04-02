package com.cqjtu.mindassess.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.*;
import com.cqjtu.mindassess.pojo.vo.systeminfo.SystemInfoVo;
import com.cqjtu.mindassess.pojo.vo.systeminfo.JavaInfo;
import com.cqjtu.mindassess.pojo.vo.systeminfo.JvmInfo;
import com.cqjtu.mindassess.pojo.vo.systeminfo.OsInfo;
import com.cqjtu.mindassess.service.ISystemInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Zfans
 */
@Service
public class SystemInfoServiceImpl implements ISystemInfoService {
    @Override
    public SystemInfoVo query() {
        cn.hutool.system.JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        cn.hutool.system.OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        SystemInfoVo systemInfoVo = new SystemInfoVo();

        OsInfo myOsInfo = new OsInfo();
        myOsInfo.setOsName(osInfo.getName());
        myOsInfo.setOsArch(osInfo.getArch());
        myOsInfo.setOsVersion(osInfo.getVersion());
        myOsInfo.setOsHostName(hostInfo.getName());
        myOsInfo.setOsHostAddress(hostInfo.getAddress());
        systemInfoVo.setOsInfo(myOsInfo);
        JavaInfo javaInfo = new JavaInfo();
        javaInfo.setJvmName(jvmInfo.getName());
        javaInfo.setJvmVersion(jvmInfo.getVersion());
        javaInfo.setJvmVendor(jvmInfo.getVendor());
        javaInfo.setJavaName(javaRuntimeInfo.getName());
        javaInfo.setJavaVersion(javaRuntimeInfo.getVersion());
        systemInfoVo.setJavaRuntimeInfo(javaInfo);
        JvmInfo myJvmInfo = new JvmInfo();
        myJvmInfo.setJvmMaxMemory(FileUtil.readableFileSize(runtimeInfo.getMaxMemory()));
        myJvmInfo.setJvmUsableMemory(FileUtil.readableFileSize(runtimeInfo.getUsableMemory()));
        myJvmInfo.setJvmTotalMemory(FileUtil.readableFileSize(runtimeInfo.getTotalMemory()));
        myJvmInfo.setJvmFreeMemory(FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        BigDecimal usedMemory = NumberUtil.sub(new BigDecimal(runtimeInfo.getTotalMemory()), new BigDecimal(runtimeInfo.getFreeMemory()));
        myJvmInfo.setJvmUsedMemory(FileUtil.readableFileSize(usedMemory.longValue()));
        BigDecimal rate = NumberUtil.div(usedMemory, runtimeInfo.getTotalMemory());
        String usedRate = new DecimalFormat("#.00").format(NumberUtil.mul(rate, 100));
        myJvmInfo.setJvmMemoryUsedRate(usedRate);
        systemInfoVo.setJvmInfo(myJvmInfo);
        return systemInfoVo;
    }
}
