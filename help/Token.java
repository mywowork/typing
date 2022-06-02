Folter{
        RepeatableFilter
        RepeatSubmitInterceptor//��ֹ�ظ��ύ������
        JwtAuthenticationTokenFilter//token������ ��֤token��Ч��

        com.ruoyi.framework.config.SecurityConfig//spring security����(Folter Ҳ����)
 }
        com.ruoyi.framework.security.handle.AuthenticationEntryPointImpl//token fill AccessDeniedException

        anyPathmatcher ������SecurityConfig

framework

/**
 * token������ ��֤token��Ч��
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
