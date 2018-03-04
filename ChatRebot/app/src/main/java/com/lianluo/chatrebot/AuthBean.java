package com.lianluo.chatrebot;

/**
 * Created by wangyaoguo on 2018/3/3.
 */


public class AuthBean {
    public String access_token;
    public String session_key;
    public String scope;
    public String refresh_token;
    public String session_secret;
    public String expires_in;

    public String error;
    public String error_description;
}

/*
      {
     "access_token": "24.ba7ad1d029fd3526847773e5af58934f.2592000.1522673989.282335-10863169",
     "session_key": "9mzdWTtEvvYogLnSWfpoHXHnlhHpxSym0zS0fs/aE5Fd5lv5yq88ZNiAjgsajgEVNpxENMpDWJiUm1ljScZ+pNjz7ZZAJw==",
     "scope": "public brain_all_scope brain_unit_utterance audio_voice_assistant_get audio_tts_post wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower bnstest_fasf lpq_开放 cop_helloScope ApsMis_fangdi_permission",
     "refresh_token": "25.1812eaa13ec4ea96434759f383099fa6.315360000.1835441989.282335-10863169",
     "session_secret": "217737d56c0c613533951c30b223d82b",
     "expires_in": 2592000
     }
     or
     {
     "error": "invalid_client",
     "error_description": "Client authentication failed"
     }
 */

