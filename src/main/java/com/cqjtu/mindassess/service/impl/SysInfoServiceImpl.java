package com.cqjtu.mindassess.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.*;
import com.cqjtu.mindassess.pojo.vo.sysinfo.SysInfoVo;
import com.cqjtu.mindassess.pojo.vo.sysinfo.SysJavaInfo;
import com.cqjtu.mindassess.pojo.vo.sysinfo.SysJvmInfo;
import com.cqjtu.mindassess.pojo.vo.sysinfo.SysOsInfo;
import com.cqjtu.mindassess.service.ISysInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
public class SysInfoServiceImpl implements ISysInfoService {
    @Override
    public SysInfoVo query() {
        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        SysInfoVo sysInfoVo = new SysInfoVo();

        SysOsInfo sysOsInfo = new SysOsInfo();
        sysOsInfo.setOsName(osInfo.getName());
        sysOsInfo.setOsArch(osInfo.getArch());
        sysOsInfo.setOsVersion(osInfo.getVersion());
        sysOsInfo.setOsHostName(hostInfo.getName());
        sysOsInfo.setOsHostAddress(hostInfo.getAddress());
        sysInfoVo.setOsInfo(sysOsInfo);
        SysJavaInfo sysJavaInfo = new SysJavaInfo();
        sysJavaInfo.setJvmName(jvmInfo.getName());
        sysJavaInfo.setJvmVersion(jvmInfo.getVersion());
        sysJavaInfo.setJvmVendor(jvmInfo.getVendor());
        sysJavaInfo.setJavaName(javaRuntimeInfo.getName());
        sysJavaInfo.setJavaVersion(javaRuntimeInfo.getVersion());
        sysInfoVo.setJavaRuntimeInfo(sysJavaInfo);
        SysJvmInfo sysJvmInfo = new SysJvmInfo();
        sysJvmInfo.setJvmMaxMemory(FileUtil.readableFileSize(runtimeInfo.getMaxMemory()));
        sysJvmInfo.setJvmUsableMemory(FileUtil.readableFileSize(runtimeInfo.getUsableMemory()));
        sysJvmInfo.setJvmTotalMemory(FileUtil.readableFileSize(runtimeInfo.getTotalMemory()));
        sysJvmInfo.setJvmFreeMemory(FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        BigDecimal usedMemory = NumberUtil.sub(new BigDecimal(runtimeInfo.getTotalMemory()), new BigDecimal(runtimeInfo.getFreeMemory()));
        sysJvmInfo.setJvmUsedMemory(FileUtil.readableFileSize(usedMemory.longValue()));
        BigDecimal rate = NumberUtil.div(usedMemory, runtimeInfo.getTotalMemory());
        String usedRate = new DecimalFormat("#.00").format(NumberUtil.mul(rate, 100));
        sysJvmInfo.setJvmMemoryUsedRate(usedRate);
        sysInfoVo.setJvmInfo(sysJvmInfo);
        return sysInfoVo;
    }
}
