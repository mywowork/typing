Folter{
        RepeatableFilter
        RepeatSubmitInterceptor//防止重复提交拦截器
        JwtAuthenticationTokenFilter//token过滤器 验证token有效性

        com.ruoyi.framework.config.SecurityConfig//spring security配置(Folter 也在这)
 }
        com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl//token fill AccessDeniedException

        anyPathmatcher 配制在SecurityConfig

framework

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
public class com.ruoyi.framework.security.filter.JwtAuthenticationTokenFilter extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain){
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
