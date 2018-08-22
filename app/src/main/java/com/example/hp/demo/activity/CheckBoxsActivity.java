package com.example.hp.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hp.demo.R;
import com.example.hp.demo.bean.AllSBLXBean;
import com.example.hp.demo.utils.GsonUtil;
import com.example.hp.demo.view.SpinnerView;

import java.util.List;

public class CheckBoxsActivity extends AppCompatActivity {

    private TextView mTv_text;
    private TextView mTv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_check_boxs);
        setContentView(R.layout.search_menu_message);
        String data = "[ {\n" +
                "  \"ID\" : \"4ACF7BFBD84F47A6978712A434DD0A84\",\n" +
                "  \"TEXT\" : \"自动感知设备\",\n" +
                "  \"child\" : [ {\n" +
                "    \"ID\" : \"E15A73D04120477F963CC66356B6238F\",\n" +
                "    \"TEXT\" : \"人群识别\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"F5332ECF9848448084CDC3EEDFA6B2CD\",\n" +
                "      \"TEXT\" : \"枪机\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"A1900FD527234D6EA661504B93F2EBB3\",\n" +
                "      \"TEXT\" : \"球机\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"6F699310F5E942988B170DE3A0BDDB3D\",\n" +
                "    \"TEXT\" : \"人脸识别\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"47FC2AE32DDB4DC1880543683E8E93C6\",\n" +
                "      \"TEXT\" : \"枪机\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"E193A9BDF27D44C7886CC7E24272F4DD\",\n" +
                "      \"TEXT\" : \"全局\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"970318BE8485490DB445A250966E01B8\",\n" +
                "    \"TEXT\" : \"射频采集\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"5DBA28346F8F4DDC9EF8C8583896887E\",\n" +
                "      \"TEXT\" : \"有线\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"DCC0A497B7D64A84A22CAB1A2439E2E8\",\n" +
                "      \"TEXT\" : \"无线\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"D0B5262D4D2C465CA74E676813FF25AC\",\n" +
                "    \"TEXT\" : \"自动感应器\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"C9027DDAF2764F4DAA157AA7D4807429\",\n" +
                "      \"TEXT\" : \"消防专线\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"15360BFB39734B80875404D604384CA4\",\n" +
                "      \"TEXT\" : \"互联网\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"2FA085F564734329923020EC685FA904\",\n" +
                "      \"TEXT\" : \"电话线\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"E423297BF1134F839253ACA52AD053DF\",\n" +
                "    \"TEXT\" : \"车脸识别\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"460CD9007C0D4490B5699CBBA046A2EB\",\n" +
                "      \"TEXT\" : \"小区\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"BB0D204A9FB34446803E40B6265AEA74\",\n" +
                "      \"TEXT\" : \"停车场\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"A03C6ED509ED490FA057EB92AB6E1E12\",\n" +
                "    \"TEXT\" : \"视频监控\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"ECA2B7ED24D2451A85C3B10CAEC77580\",\n" +
                "      \"TEXT\" : \"枪机\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"9AF80A89835F4570BBE5EDDDA9AE2FCF\",\n" +
                "      \"TEXT\" : \"球机\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"DEF0543C9EA647EFA02CE4F27BAC8083\",\n" +
                "      \"TEXT\" : \"鱼眼\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"1E345F8355BC4B82BD98AB3897369635\",\n" +
                "    \"TEXT\" : \"MAC采集\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"2F6E3D70141B479FB85B0D6E59A2F960\",\n" +
                "      \"TEXT\" : \"非经\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"4D2E9BCF2019497AAA08BB4E9805876E\",\n" +
                "      \"TEXT\" : \"独立\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"ADB602D18C4546A7B8FDFDAEDCA5503D\",\n" +
                "      \"TEXT\" : \"二合一\"\n" +
                "    } ]\n" +
                "  } ]\n" +
                "}, {\n" +
                "  \"ID\" : \"6D16014232AD4099A4B12209A81253BF\",\n" +
                "  \"TEXT\" : \"静态警用设备\",\n" +
                "  \"child\" : [ {\n" +
                "    \"ID\" : \"6B13FA6260964F00A4C747777C8B3150\",\n" +
                "    \"TEXT\" : \"防冲撞设施\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"828A6EBB9ED745949BEBCDC88BAEF62B\",\n" +
                "      \"TEXT\" : \"移动\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"8DB1C913C32B4DEC954B7DEA474BE746\",\n" +
                "      \"TEXT\" : \"固定\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"2044F89FD31C48B2852E7FABD9A82FA8\",\n" +
                "    \"TEXT\" : \"四联三防装备\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"4F67197CAAE34E8C9A869CB5B2A02A25\",\n" +
                "      \"TEXT\" : \"常规防护套装\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"5FE9D2A3937141ACB87045A517337ABD\",\n" +
                "    \"TEXT\" : \"消防设施\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"B9FEE04A195B4EEA8CB37B436E580FB7\",\n" +
                "      \"TEXT\" : \"灭火器\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"DC16A9DF18074F38B33230070BE60AA8\",\n" +
                "      \"TEXT\" : \"消防栓\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"E99B3E5A08634539B8C2018AA2C74645\",\n" +
                "    \"TEXT\" : \"反恐紧急按钮\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"A3BCC4B3AD524974A765DFC1D2C0D390\",\n" +
                "      \"TEXT\" : \"喇叭\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"5A655C00B966438EBAE57223F2D96955\",\n" +
                "      \"TEXT\" : \"按钮\"\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"ID\" : \"BF062EBFB5734BDC9C069854A6F54613\",\n" +
                "    \"TEXT\" : \"警用场所\",\n" +
                "    \"child\" : [ {\n" +
                "      \"ID\" : \"E75865F014A1425FBC7506090F1082F3\",\n" +
                "      \"TEXT\" : \"消防队\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"0DAE18CFF9824BF69804C33E745D7D1A\",\n" +
                "      \"TEXT\" : \"依靠力量岗亭\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"1A7418F31E064E6C93835540FE5388FE\",\n" +
                "      \"TEXT\" : \"派出所\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"FF466FF816404FBF839905D172B6E0E0\",\n" +
                "      \"TEXT\" : \"警务室\"\n" +
                "    }, {\n" +
                "      \"ID\" : \"36C3484EC7F84D8EB2A41EF7FB2D18F0\",\n" +
                "      \"TEXT\" : \"警亭\"\n" +
                "    } ]\n" +
                "  } ]\n" +
                "} ]";

        List<AllSBLXBean> allSBLXBeen = GsonUtil.getInstance().jsonToList(data, AllSBLXBean[].class);
        final SpinnerView spinnerview = (SpinnerView) findViewById(R.id.spinnerView);
        spinnerview.initAdapter(this, allSBLXBeen);
        Button btndowload = (Button) findViewById(R.id.btn_searchlx);
        mTv_id = (TextView) findViewById(R.id.tv_id);
        mTv_text = (TextView) findViewById(R.id.tv_text);
        btndowload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItemText = spinnerview.getSelectedItemText();
                String selectedItemID = spinnerview.getSelectedItemID();
                mTv_id.setText(selectedItemID);
                mTv_text.setText(selectedItemText);

            }
        });

    }
}
