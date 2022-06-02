//ruoyi-quartz
com.ruoyi.quartz.domain.SysJob@3a3e8233[
  jobId=2
  jobName=系统默认（有参）
  jobGroup=DEFAULT
  cronExpression=0/15 * * * * ?
  nextValidTime=Thu May 26 19:20:45 CST 2022
  misfirePolicy=3
  concurrent=1
  status=1
  createBy=admin
  createTime=Thu May 26 11:18:26 CST 2022
  updateBy=<null>
  updateTime=<null>
  remark=
]
//收到提交的任务(com.ruoyi.quartz.controller.SysJobController)
SysJobController.run()
//调用服务层执行
SysJobServiceImpl.run()
//提交任务
((org.quartz.Scheduler)scheduler).triggerJob()
//执行任务(com.ruoyi.quartz.util.AbstractQuartzJob)
AbstractQuartzJob.execute(){
	//执行前处理
	before(context, sysJob);
    doExecute(context, sysJob);
	{
		//反射执行
		Method method = bean.getClass().getDeclaredMethod(methodName);
        method.invoke(bean);
	}
	//执行后处理
    after(context, sysJob, null)
	{
		// 写入数据库当中
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLog);
	}
}

@RequestMapping("/monitor/job")
public class SysJobController extends BaseController{
	    /**
     * 定时任务立即执行一次
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return AjaxResult.success();
    }
}
/**
 * 定时任务调度信息 服务层
 * 
 * @author ruoyi
 */
@Service
public class SysJobServiceImpl implements ISysJobService
{
    /**
     * 立即运行任务
     * 
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(SysJob job) throws SchedulerException
    {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        SysJob properties = selectJobById(job.getJobId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        ((org.quartz.Scheduler)scheduler).triggerJob(com.ruoyi.quartz.util.ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
    }
}
class com.ruoyi.quartz.util.AbstractQuartzJob{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        SysJob sysJob = new SysJob();
        BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try
        {
            before(context, sysJob);
            if (sysJob != null)
            {
                doExecute(context, sysJob);
            }
            after(context, sysJob, null);
        }
        catch (Exception e)
        {
            log.error("任务执行异常  - ：", e);
            after(context, sysJob, e);
        }
    }
	protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
	{
		JobInvokeUtil.invokeMethod(sysJob);
	}	
}

public class JobInvokeUtil
{
    /**
     * 执行方法
     *
     * @param sysJob 系统任务
     */
    public static void invokeMethod(SysJob sysJob) throws Exception
    {
        String invokeTarget = sysJob.getInvokeTarget();
        String beanName = getBeanName(invokeTarget);
        String methodName = getMethodName(invokeTarget);
        List<Object[]> methodParams = getMethodParams(invokeTarget);

        if (!isValidClassName(beanName))
        {
            Object bean = SpringUtils.getBean(beanName);
            invokeMethod(bean, methodName, methodParams);
        }
        else
        {
            Object bean = Class.forName(beanName).newInstance();
            invokeMethod(bean, methodName, methodParams);
        }
    }
    /**
     * 调用任务方法
     *
     * @param bean 目标对象
     * @param methodName 方法名称
     * @param methodParams 方法参数
     */
    private static void invokeMethod(Object bean, String methodName, List<Object[]> methodParams)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException
    {
        if (StringUtils.isNotNull(methodParams) && methodParams.size() > 0)
        {
            Method method = bean.getClass().getDeclaredMethod(methodName, getMethodParamsType(methodParams));
            method.invoke(bean, getMethodParamsValue(methodParams));
        }
        else
        {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }
}

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}