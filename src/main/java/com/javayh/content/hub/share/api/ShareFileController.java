package com.javayh.content.hub.share.api;

import com.javayh.content.hub.common.Result;
import com.javayh.content.hub.share.bo.ShareLinkBo;
import com.javayh.content.hub.share.service.ShareFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 分享
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-30
 */
@RestController
@RequestMapping(value = "/share")
public class ShareFileController {

    @Autowired
    private ShareFileService shareFileService;

    /**
     * share 最后的计算
     *
     * @param shareLinkBo 需要share的逻辑实体
     * @return
     */
    @PostMapping(value = "/bc")
    public Result shareItems(@RequestBody ShareLinkBo shareLinkBo) {
        return Result.ok(shareFileService.shareItems(shareLinkBo));
    }

    /**
     * 验证期合法性
     *
     * @param i 唯一标识
     * @param p 加密的密码
     * @return
     */
    @GetMapping(value = "/verify")
    public Result verify(String i, String p) {
        return Result.ok(shareFileService.verify(i, p));
    }


    /**
     * @param i 唯一标识
     * @param p 加密的密码
     * @return
     */
    @GetMapping(value = "/list")
    public Result shareItemsList(String i, String p) {
        return Result.ok(shareFileService.shareItemsList(i, p));
    }
}
