package pl.java.scalatech.metrics;

import static java.lang.Double.NaN;
import static java.lang.management.ManagementFactory.getOperatingSystemMXBean;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricSet;

class OperatingSystemGaugeSet implements MetricSet {
 
    private static final String PROCESS_CPU_LOAD = "processCpuLoad";
	private static final String SYSTEM_CPU_LOAD = "systemCpuLoad";
	private static final String FD_USAGE = "fd.usage";
	private static final String TOTAL_PHYSICAL_MEMORY_SIZE = "totalPhysicalMemorySize";
	private static final String FREE_PHYSICAL_MEMORY_SIZE = "freePhysicalMemorySize";
	private static final String PROCESS_CPU_TIME = "processCpuTime";
	private static final String FREE_SWAP_SPACE_SIZE = "freeSwapSpaceSize";
	private static final String TOTAL_SWAP_SPACE_SIZE = "totalSwapSpaceSize";
	private static final String COMMITTED_VIRTUAL_MEMORY_SIZE = "committedVirtualMemorySize";
	private final OperatingSystemMXBean mxBean;
    private final Optional<Method> committedVirtualMemorySize;
    private final Optional<Method> totalSwapSpaceSize;
    private final Optional<Method> freeSwapSpaceSize;
    private final Optional<Method> processCpuTime;
    private final Optional<Method> freePhysicalMemorySize;
    private final Optional<Method> totalPhysicalMemorySize;
    private final Optional<Method> openFileDescriptorCount;
    private final Optional<Method> maxFileDescriptorCount;
    private final Optional<Method> systemCpuLoad;
    private final Optional<Method> processCpuLoad;
 
   
    public OperatingSystemGaugeSet() {
        this(getOperatingSystemMXBean());
    }
 
   
    public OperatingSystemGaugeSet(OperatingSystemMXBean mxBean) {
        this.mxBean = mxBean;
        committedVirtualMemorySize = getMethod("getCommittedVirtualMemorySize");
        totalSwapSpaceSize = getMethod("getTotalSwapSpaceSize");
        freeSwapSpaceSize = getMethod("getFreeSwapSpaceSize");
        processCpuTime = getMethod("getProcessCpuTime");
        freePhysicalMemorySize = getMethod("getFreePhysicalMemorySize");
        totalPhysicalMemorySize = getMethod("getTotalPhysicalMemorySize");
        openFileDescriptorCount = getMethod("getOpenFileDescriptorCount");
        maxFileDescriptorCount = getMethod("getMaxFileDescriptorCount");
        systemCpuLoad = getMethod("getSystemCpuLoad");
        processCpuLoad = getMethod("getProcessCpuLoad");
    }
 
 
    @Override
    public Map<String, Metric> getMetrics() {
        final Map<String, Metric> gauges = new HashMap<>();
        gauges.put(COMMITTED_VIRTUAL_MEMORY_SIZE, (Gauge<Long>) () -> invokeLong(committedVirtualMemorySize));
        gauges.put(TOTAL_SWAP_SPACE_SIZE, (Gauge<Long>) () -> invokeLong(totalSwapSpaceSize));
        gauges.put(FREE_SWAP_SPACE_SIZE, (Gauge<Long>) () -> invokeLong(freeSwapSpaceSize));
        gauges.put(PROCESS_CPU_TIME, (Gauge<Long>) () -> invokeLong(processCpuTime));
        gauges.put(FREE_PHYSICAL_MEMORY_SIZE, (Gauge<Long>) () -> invokeLong(freePhysicalMemorySize));
        gauges.put(TOTAL_PHYSICAL_MEMORY_SIZE, (Gauge<Long>) () -> invokeLong(totalPhysicalMemorySize));
        gauges.put(FD_USAGE, (Gauge<Double>) () -> invokeRatio(openFileDescriptorCount, maxFileDescriptorCount));
        gauges.put(SYSTEM_CPU_LOAD, (Gauge<Double>) () -> invokeDouble(systemCpuLoad));
        gauges.put(PROCESS_CPU_LOAD, (Gauge<Double>) () -> invokeDouble(processCpuLoad));
        return gauges;
    }
 
    private Optional<Method> getMethod(String name) {
        try {
            final Method method = mxBean.getClass().getDeclaredMethod(name);
            method.setAccessible(true);
            return of(method);
        } catch (NoSuchMethodException e) {
            return empty();
        }
    }
 
    private long invokeLong(Optional<Method> method) {
        if (method.isPresent()) {
            try {
                return (long) method.get().invoke(mxBean);
            } catch (IllegalAccessException | InvocationTargetException ite) {
                return 0L;
            }
        }
        return 0L;
    }
 
    private double invokeDouble(Optional<Method> method) {
        if (method.isPresent()) {
            try {
                return (double) method.get().invoke(mxBean);
            } catch (IllegalAccessException | InvocationTargetException ite) {
                return 0.0;
            }
        }
        return 0.0;
    }
 
    private double invokeRatio(Optional<Method> numeratorMethod, Optional<Method> denominatorMethod) {
        if (numeratorMethod.isPresent() && denominatorMethod.isPresent()) {
            try {
                long numerator = (long) numeratorMethod.get().invoke(mxBean);
                long denominator = (long) denominatorMethod.get().invoke(mxBean);
                if (0 ==  denominator) {
                    return NaN;
                }
                return 1.0 * numerator / denominator;
            } catch (IllegalAccessException | InvocationTargetException ite) {
                return NaN;
            }
        }
        return NaN;
    }
 
}