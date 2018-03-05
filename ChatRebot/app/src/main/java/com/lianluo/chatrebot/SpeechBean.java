package com.lianluo.chatrebot;

import java.util.List;

/**
 * Created by wangyaoguo on 2018/3/5.
 */

/*{
 "sn":1,"ls":false,"bg":0,"ed":0,"ws":[{"bg":0,"cw":[{"sc":0.0,"w":"留"}]},{"bg":0,"cw":[{"sc":0.0,"w":"取"}]},{"bg":0,"cw":[{"sc":0.0,"w":"丹"}]},{"bg":0,"cw":[{"sc":0.0,"w":"心"}]},{"bg":0,"cw":[{"sc":0.0,"w":"照"}]},{"bg":0,"cw":[{"sc":0.0,"w":"汗青"}]}]}
{"sn":2,"ls":true,"bg":0,"ed":0,"ws":[{"bg":0,"cw":[{"sc":0.0,"w":"。"}]}]}
*/

public class SpeechBean {
    public int sn;
    public boolean ls;
    public int bg;
    public int ed;
    public List<WsBean> ws;

    public class WsBean {
        public int bg;
        public List<CwBean> cw;

        public class CwBean {
            public float sc;
            public String w;
        }
    }
}

/*
{
  "sn": 1,
  "ls": false,
  "bg": 0,
  "ed": 0,
  "ws": [
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "留"
        }
      ]
    },
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "取"
        }
      ]
    },
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "丹"
        }
      ]
    },
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "心"
        }
      ]
    },
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "照"
        }
      ]
    },
    {
      "bg": 0,
      "cw": [
        {
          "sc": 0.0,
          "w": "汗青"
        }
      ]
    }
  ]
}
 */
