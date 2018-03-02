package com.lianluo.chatrebot;

import java.util.List;

/**
 * Created by wangyaoguo on 2018/3/2.
 */

public class ChatBean {
    public String log_id;
    public String error_code;
    public String error_msg;
    public ResultBean result;

    public class ResultBean {
        public String session_id;
        public List<DetailBean> action_list;

        public class DetailBean {
            public String action_id;
            public String say;
        }
    }
}

/*
{
    "log_id": 151997712040475,
    "result": {
        "action_list": [
            {
                "action_id": "0",
                "action_type": {
                    "act_type": "",
                    "act_target": "",
                    "act_target_detail": "",
                    "act_type_detail": ""
                },
                "arg_list": [],
                "confidence": 99,
                "exe_status": [],
                "main_exe": "",
                "say": "还是家庭温馨版的科幻片有木有！"
            },
            {
                "action_id": "0",
                "action_type": {
                    "act_type": "",
                    "act_target": "",
                    "act_target_detail": "",
                    "act_type_detail": ""
                },
                "arg_list": [],
                "confidence": 98,
                "exe_status": [],
                "main_exe": "",
                "say": "嗯，挺好看的我觉得，就是没拍完"
            },
            {
                "action_id": "0",
                "action_type": {
                    "act_type": "",
                    "act_target": "",
                    "act_target_detail": "",
                    "act_type_detail": ""
                },
                "arg_list": [],
                "confidence": 97,
                "exe_status": [],
                "main_exe": "",
                "say": "比较晦涩的科幻剧"
            }
        ],
        "bot_session": "",
        "session_id": "771a482c3e4347c386684066adc23dd71"
    }
}
 */