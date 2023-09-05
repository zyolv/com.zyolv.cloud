package com.zyolv.cloud.Controller;


import com.zyolv.cloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("/oauth")
public class AuthController  {
    //令牌请求的端点
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    //自定义异常翻译器，针对用户名、密码异常，授权类型不支持的异常进行处理
    //private OAuthServerWebResponseExceptionTranslator  translate;

    /**
     * 重写/oauth/token这个默认接口，返回的数据格式统一
     */
    @PostMapping(value = "/token")
    public CommonResult<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("success");
        commonResult.setData(accessToken);
        return commonResult;
    }

    /**
     * 重写/oauth/check_token这个默认接口，用于校验令牌，返回的数据格式统一
     */
    @PostMapping(value = "/check_token")
    public CommonResult<Map<String,?>> checkToken(@RequestParam("token") String value)  {
        Map<String, ?> map = checkTokenEndpoint.checkToken(value);
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("success");
        commonResult.setData(map);
        return commonResult;
    }


}
