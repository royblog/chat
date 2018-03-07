package com.lianluo.chatrebot;

import java.util.List;

/**
 * Created by wangyaoguo on 2018/3/2.
 */

public class ChatBean {

    //error
    public String error_code = "";
    public String error_msg = "";

    //success
    public ResultBean result;

    public class ResultBean {
        public String session_id;
        public List<ResultDetailBean> action_list;
        public QuresBean qu_res;

        public class ResultDetailBean {
            public String main_exe; //触发函数
            public String say; //返回语句
        }

        public class QuresBean {
            public String raw_query; //问题
            public List<CandidatesBean> intent_candidates;

            public class CandidatesBean {
                public String func_slot;
                public String intent;   //意图
                public boolean intent_need_clarify; //是否需要澄清
                public String match_info; //匹配模板
            }
        }
    }
}

/*
{
    "error_msg": "Access token invalid or no longer valid",
    "error_code": 110
}
*/



/*
{
    "log_id": 152040762749100,
    "result": {
        "action_list": [
            {
                "action_id": "ai_translate_unit_satisfy",
                "action_type": {
                    "act_target": "",
                    "act_target_detail": "",
                    "act_type": "satisfy",
                    "act_type_detail": ""
                },
                "arg_list": [],
                "code_actions": {},
                "confidence": 1,
                "exe_status": [],
                "hint_list": [],
                "main_exe": "",
                "say": "正在翻译"
            }
        ],
        "qu_res": {
            "intent_candidates": [
                {
                    "extra_info": null,
                    "from_who": "smart_qu",
                    "func_slot": "",
                    "intent": "AI_TRANSLATE_UNIT",
                    "intent_confidence": 100,
                    "intent_need_clarify": false,
                    "match_info": "[W:0-100]翻译[W:0-10] ",
                    "slots": []
                }
            ],
            "lexical_analysis": [
                {
                    "basic_word": [
                        "桌子"
                    ],
                    "term": "桌子",
                    "type": "21",
                    "weight": 0.5620096921920776
                },
                {
                    "basic_word": [
                        "翻译"
                    ],
                    "term": "翻译",
                    "type": "36",
                    "weight": 0.4379903674125671
                }
            ],
            "log_id": "openapi_152040762749100_18518",
            "raw_query": "桌子翻译",
            "sentiment_analysis": {
                "label": 2,
                "pval": 0.0500043
            },
            "status": 0,
            "timestamp": 1
        },
        "schema": {
            "bot_merged_slots": [],
            "current_qu_intent": "AI_TRANSLATE_UNIT",
            "intent_confidence": 100
        },
        "session_id": "0314cc1e732b4ba0b5fa6d68b4a595551"
    }
}
 */