package com.zyolv.cloud.Controller;


import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.AuthTokenReq;
import com.zyolv.cloud.service.RoleUserService;
import com.zyolv.cloud.service.UserService;
import com.zyolv.cloud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/oauth")
public class AuthController {
    //令牌请求的端点
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleUserService roleUserService;

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

    @PostMapping("/api/token")
    public CommonResult<OAuth2AccessToken> getToken(@RequestBody AuthTokenReq authTokenReq) throws HttpRequestMethodNotSupportedException {
        Map<String, String> params = new HashMap<>();
        params.put("username", authTokenReq.getUsername());
        params.put("password", authTokenReq.getPassword());
        params.put("grant_type", authTokenReq.getGrantType());
        params.put("client_id", authTokenReq.getClientId());
        params.put("client_secret", authTokenReq.getClientSecret());


        //生成已经认证的client
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authTokenReq.getClientId(), authTokenReq.getClientSecret(), new ArrayList<>());

        ResponseEntity<OAuth2AccessToken> oAuth2AccessToken = tokenEndpoint.postAccessToken(token, params);

        return CommonResult.success(oAuth2AccessToken.getBody().getValue());


    }

    @PostMapping("/logout")
    @ResponseBody

    public CommonResult getCollUser() {




        return CommonResult.success("success");
    }



    @PostMapping("/add")
    @ResponseBody
    public CommonResult addUser(@RequestBody UserEntity userEntity){
        if (userService.getUserByUsername(userEntity.getUsername()) != null) return CommonResult.fail(400,"fail","用户已存在");

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userService.saveUser(userEntity);
//        System.out.println(userService.getUserByUsername(userName));
        //roleUserService.saveRoleUser(userService.getUserByUsername(userName).getId(),roleId);
        return CommonResult.success("success");
    }

    /**
     * 重写/oauth/check_token这个默认接口，用于校验令牌，返回的数据格式统一
     */
    @PostMapping(value = "/check_token")
    public CommonResult<Map<String, ?>> checkToken(@RequestParam("token") String value) {
        Map<String, ?> map = checkTokenEndpoint.checkToken(value);
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(200);
        commonResult.setMessage("success");
        commonResult.setData(map);
        return commonResult;
    }


}
