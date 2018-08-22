package com.example.hp.demo.constant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.demo.R;
import com.example.hp.demo.utils.EncodingHandler;
import com.example.hp.demo.utils.ExternalStorageUtil;
import com.example.hp.demo.utils.FileUtil;
import com.example.hp.demo.view.CustomDialog;

import java.io.File;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Static {
    //    public static final String THEURL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName=北京";
    public static final String THEURL = "https://way.jd.com/jisuapi/driverexamQuery";//驾照
    public static final int HTTP_TIMEOUT = 50 * 1000;
    public static final Object SWITCH_MODE_KEY = "mode_key";
    public static String appkey = "109a15e4cede86cab981bbf19bcf2621";
    public static final String BASE_URL = "https://way.jd.com/jisuapi/get";//新闻
    public static final String BASE_URL1 = "https://127.0.0.1:38090/wwgk/appport/csxxlist ";//场所列表
    public static final String HTTP_KEY = "109a15e4cede86cab981bbf19bcf2621";

    public static final String APP_SHORT_NAME = "demo";
    public static final String ZL_SD_HOME = "fzj";

    public static final String SDCARD_APP_ROOT = Environment.getExternalStoragePublicDirectory(
            ZL_SD_HOME + File.separator + APP_SHORT_NAME).getAbsolutePath();
    public static final String HOME_CACHE = "cache";
    public static final String HOME_CACHE_IMAGE = "cache" + File.separator + "image";
    public static final String HOME_CACHE_ATTACH = "cache" + File.separator + "attach";
    public static final String HOME_LIC = "lic";

    public static final String SD_CARD_FILE_PATH = "file://";
    public final static String map_online_url = "http://127.0.0.1" + ":" + "8088" + "/PGIS_S_TileMapServer/Maps/PGISSL";

    public static final String HOME_CACHE_LOG = "cache" + File.separator + "log";

    public static final int FROM_TAKE_PHOTO = 200;
    public static final int FROM_ALBUM = 100;
    public static final int FROM_FILE = 300;
    private static CustomDialog mDialogWaiting;

    public static String TencentHomePage = "http://ac.qq.com/";
    private static Bitmap qrCodeBitmap;

    public static final String newsdata = "{\n" +
            "    \"code\": \"10000\",\n" +
            "    \"charge\": false,\n" +
            "    \"msg\": \"查询成功\",\n" +
            "    \"result\": {\n" +
            "        \"status\": \"0\",\n" +
            "        \"msg\": \"ok\",\n" +
            "        \"result\": {\n" +
            "            \"channel\": \"头条\",\n" +
            "            \"num\": \"30\",\n" +
            "            \"list\": [\n" +
            "                {\n" +
            "                    \"title\": \"频繁按油枪有猫腻？中石化释疑：油箱有气需手动\",\n" +
            "                    \"time\": \"2018-07-26 01:11:00\",\n" +
            "                    \"src\": \"新浪财经综合\",\n" +
            "                    \"category\": \"finance\",\n" +
            "                    \"pic\": \"\",\n" +
            "                    \"content\": \"<span style=\\\"font-family: KaiTi_GB2312, KaiTi;\\\">频繁按油枪有猫腻？中石化释疑</span>来源：北京晨报昨天，一段视频网络疯传，视频中加油站工作人员在给一辆车加油时不断捏手中油枪头，拍摄者认为这样“有猫腻”，不少网友看了也质疑，如此加油会“多走字少加油”。昨日，北京晨报记者来到事发加油站，工作人员回应说夏季气温高，油箱有气会触动感应器，导致自动加油总失败，所以改为手动加，操作没问题，也不会对价钱和油量有任何影响。　<strong>网友质疑加油站“有猫腻”</strong>视频发上网后，不少网友纳闷儿这到底是在干吗，“见过这么操作加油枪，但没看见过捏得这么频繁的”。不少人质疑，“这么操作会损油吗？连国营加油站都会缺斤短两？”也有司机表示，这个操作是因“油箱快满了，老跳枪，工作人员想多给加油，所以捏着往里加”。经常用加油站自助机加油的张先生感觉，油枪停止加油后，油表会因惯性再走一些字，但那些都微不足道，“我自己加油时候也遇到过因为油箱里顶着气导致加油阀门自己跳开的情况，所以也需要这么来回按几下。”另外也有人认为，加油站是在“坑”司机钱的质疑不可信，“工作人员加多加少，钱也不会进自己兜里，不应该有这样的恶意揣测，这个操作很常见，没有什么问题。”另有网友质疑，是不是被加油的车辆油箱构造不同，或者是其他别的因素，导致工作人员需要反复捏阀门。　<strong>加油站：油箱有气需手动</strong>昨天，记者来到中石化东大桥加油站，工作人员都在忙碌地给汽车加油。记者观察发现，有的是直接把油枪插入汽车油箱后，也有工作人员如视频中操作的一样反复捏几下油枪阀门。一名工作人员得知记者来意后，表示已经看过网传的视频，该工作人员解释道：“按照油枪的工作原理，自动加油时如果加满了，油碰到感应器，油枪就关闭，停止加油了。但感应器特别灵敏，像这几天天气这么热，里面就会有很多气，气顶着感应器，也会让油枪停止工作。”因此工作人员需要手动捏压油枪的阀门来给车辆加油，“也就是变成了手动加油”。对于大家疑惑的这一做法会不会导致油量没增但计量器还在走字的情况发生，工作人员肯定的告诉记者，“那肯定不会，手动加油和自动加油是一样的，其实我们更愿意自动加，但是气顶着没办法。我们加油站是直营的，也开了20多年了，这方面请您放心。”<strong>客服：频繁跳枪加油数量无明显差异</strong>中国石化北京客服中心工作人员回复记者称，加油时反复跳枪主要有以下几个原因，“一是加油的速度过快，冲起来的油液泡沫接触到了通气孔。第二个原因是加油管通气孔的回气管堵塞或者变形，导致排气不畅。第三个原因是加油管路堵塞或不畅，使得油品不能迅速流出，滞留在油箱口附近。另外员工加油时如果没有卡住油枪档位，也需要重新打开加油枪，这些现象都是加油过程中容易发生的，不会影响加油数量。”对于记者提出的短时间内频繁多次跳枪是否正常，对方也十分肯定地表示，“不会影响加油数量，近年来许多媒体都在正规加油站现场进行了加油实验，结果表明连续加油和频繁跳枪的情况下，加油的数量没有明显差异。”记者查资料发现，通过频繁开关阀门来实现“偷油”，还需要人为修改加油机上的传感程序。但在北京，计量部门对加油站每个油枪都有严格的检查，允许误差在千分之一到千分之三，检查完毕都会在机器上铅封，个人拆封改动属于违法行为，而且会立刻被执法人员发现。北京晨报现场新闻记者&nbsp;张静姝\",\n" +
            "                    \"url\": \"http://finance.sina.cn/chanjing/gsxw/2018-07-26/detail-ihfvkitw6764192.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://finance.sina.com.cn/chanjing/gsnews/2018-07-26/doc-ihfvkitw6764192.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"党报刊文：中国不仅能跨越中等收入陷阱 还助力他国\",\n" +
            "                    \"time\": \"2018-07-26 06:16:00\",\n" +
            "                    \"src\": \"人民日报\",\n" +
            "                    \"category\": \"finance\",\n" +
            "                    \"pic\": \"\",\n" +
            "                    \"content\": \"<span style=\\\"font-family: KaiTi_GB2312, KaiTi;\\\">中国有能力跨越“中等收入陷阱”（人民要论）</span>人民日报 王 文（作者为中国人民大学重阳金融研究院执行院长、教授）“中等收入陷阱”是2007年世界银行在其报告《东亚复兴：关于经济增长的观点》中首次提出的概念。在提出初期，这个概念并没有引起经济学界的关注。2008年国际金融危机爆发后，西方国家经济陷入持续低迷，中国经济增速也从高速转向中高速，关于“中等收入陷阱”的担心尤其是中国会不会落入“中等收入陷阱”逐渐成为舆论热点。然而，在讨论的过程中，人们很容易被这个概念的“逻辑陷阱”所误导，甚至对国家未来产生不必要的悲观情绪。其实，任何一个负责任的经济学者经过客观研究都不难得出结论：中国不会落入“中等收入陷阱”。<strong>“中等收入陷阱”概念存在不少“逻辑陷阱”</strong>按照世界银行在特定历史背景下提出的这一概念的语义，“中等收入陷阱”是指许多国家在成为中等收入国家之后，将会进入经济停滞期，出现贫富悬殊、环境恶化、社会动荡等现象，最终长期徘徊在高收入国家的门槛之外。深究这个概念，可以发现其缺乏严谨的理论论证，存在不少“逻辑陷阱”。一是“统计陷阱”。“中等收入陷阱”用人均国民总收入（GNI）这个单一指标来衡量发展水平，但GNI并不能全面反映一个国家的国民生活质量、精神状态等。从联合国公布的各国国民幸福指数排名看，被视为落入“中等收入陷阱”的巴西、墨西哥、阿根廷的排名显著高于法国、西班牙、意大利等。不丹属于中低收入国家，但有关调查却认为它是“全球最幸福国家”之一。皮尤中心2014年的调查显示，在高收入的西方发达国家，对未来表示乐观的国民比例普遍在30%以下；而在孟加拉、巴西、越南等发展中国家，对未来持乐观态度的国民比例一般都在80%以上。阿玛蒂亚·森、罗伯特·巴罗等著名经济学家都认为，所谓“中等收入陷阱”只是一种统计现象，并没有经济学方法论支撑，因而相当片面。二是“对照陷阱”。“中等收入”标准往往是与美国对照、用美元衡量得出的。比如，按美国经济学家胡永泰的说法，人均国民总收入是美国水平55%以上的国家为“高收入国家”，相当于美国水平20%—55%的为“中等收入国家”，是美国水平20%以下的为“低收入国家”。通行的中等收入标准是按照汇率法计算、用美元衡量的，例如，人均国民总收入在4126美元至12735美元之间的为中等偏上收入国家。这样的对照和衡量方法有失偏颇，并不能全面反映一个国家的总产出和经济社会发展水平。三是“历史陷阱”。目前对于“中等收入陷阱”的观察，主要局限在第二次世界大战后到21世纪初的时间段。在这个时间段，许多发展中国家刚刚实现独立，处于现代国家成长的初级阶段与发展探索期，难免会有不少国家走弯路。因此，不能过早地对这些国家盖棺定论，认定其会长期陷入“中等收入陷阱”。其实，能否尽快跨过“中等收入”这道坎，关键在于能否保持持续发展的势头。而且，成为高收入国家也并不代表着未来就会高枕无忧。目前，有的高收入国家已经陷入低增长困境，人民生活水平长期徘徊不前。因此，需要用动态的、发展的眼光来观察“中等收入陷阱”。对当下的中国来说，如何营造有利于平稳健康发展的国内外环境，才是更有意义的讨论话题。<strong>中国不会重蹈一些拉美国家覆辙</strong>在关于“中等收入陷阱”的讨论中，一些拉美国家常常被视为典型案例。这些国家落入“中等收入陷阱”后所暴露出的问题集中表现在经济增长过度依赖资源开发、科学技术落后、贫富差距悬殊，既无法与低收入国家竞争，也难以缩小与高收入国家的差距。而观察中国不难发现，我们早已吸取一些拉美国家发展中的教训，不仅没有走上拉美国家的老路，而且走出了新型工业化、新型城镇化的新路，即将全面建成小康社会，进而开启全面建设社会主义现代化国家新征程。大力转变发展方式，走新型工业化道路。坚持绿色发展，是我国坚持走新型工业化道路的生动体现。近年来，我国绿色金融蓬勃发展。具体而言，绿色金融通过信贷、债券、基金、保险等工具和政策，将资金引导到环保、节能、清洁能源、绿色交通、绿色建筑等项目中。目前，我国的绿色金融已经对绿色投资产生了巨大推动作用，绿色低碳循环发展的经济体系正在加快建立。大力推进以人为核心的新型城镇化，更加注重提升人民群众获得感、幸福感、安全感。一些拉美国家在城市化过程中，由于政府公共职能缺失，导致城市化进程与工业化发展水平不相适应，大量人口涌入城市却找不到工作，出现了许多贫民窟，引发了很多社会矛盾。相比之下，我国的城镇化始终在有序推进。特别是党的十八大以来，我国大力推进以人为核心的新型城镇化，优化城镇布局，努力创造更多城镇就业岗位，工农互促、城乡互补、全面融合、共同繁荣的新型工农城乡关系加速形成。大力保障和改善民生，采取切实措施提高城乡居民衣、食、住、行水平，并且实施精准扶贫、精准脱贫，到2020年将使现行标准下农村贫困人口实现脱贫、贫困县全部摘帽。这一系列举措极大提升了人民群众的获得感、幸福感、安全感。大力振兴实体经济，加快建设制造强国。近年来，我国一直把振兴实体经济和推进产业转型升级放在重要位置，牢牢抓住新一轮科技革命和产业变革的历史机遇。我国的产业链向着中高端方向加速迈进，在新一代信息技术产业、高档数控机床和机器人、航空航天装备、海洋工程装备等诸多重点领域，我国企业正走向世界前列。加快建设制造强国，无疑将为我国跨越“中等收入陷阱”打下坚实的经济基础和产业根基。上述这些举措能够保证我国不会重蹈一些拉美国家覆辙，顺利跨越“中等收入陷阱”。事实上，我国还采取了一系列重大改革发展举措，确保经济平稳健康发展和社会和谐稳定，推动发展不断迈上新台阶。<strong>在高质量发展中跨越“中等收入陷阱”</strong>中国特色社会主义进入了新时代，我国经济发展也进入了新时代，基本特征就是我国经济已由高速增长阶段转向高质量发展阶段。推动高质量发展是当前和今后一个时期确定发展思路、制定经济政策、实施宏观调控的根本要求。我国在高质量发展方面取得的进展，表明我国完全有能力跨越“中等收入陷阱”。内生增长动力不断增强，经济结构持续优化。党的十八大以来，我国政府加大简政放权力度，转变政府职能、提高政府效能，营造有利于企业自主经营、消费者自主消费的公平市场环境。针对经济领域存在的供给侧、结构性、体制性矛盾和问题，提出把推进供给侧结构性改革作为经济工作的主线，去产能、去库存、去杠杆、降成本、补短板等重点任务有序推进，无效和低端供给明显减少，有效和中高端供给日益扩大，市场主体活力显著增强。随着供给侧结构性改革的深入推进，在“三去一降一补”基础上加快实施“破”“立”“降”，即破除无效供给、培育新动能、降低实体经济成本及制度性交易成本。这些举措正在产生积极效果，我国经济内生动力不断增强、结构持续优化。深入实施创新驱动发展战略，创新动力充沛。落入“中等收入陷阱”的国家有一个共同特征：在经历了初始阶段的高速发展后，经济发展无法实现从要素驱动向创新驱动转变，逐渐失去发展动力，因而陷于停滞。我国高度重视创新在经济发展中的引领作用，深入实施创新驱动发展战略，创新型国家建设成果丰硕，天宫、蛟龙、天眼、悟空、墨子、大飞机等重大科技成果相继问世。随着创新驱动发展战略的深入实施以及大众创业、万众创新的持续推进，我国将迎来新一轮发展，实现新的历史性跨越。大力实施区域协调发展战略，发展的均衡性显著增强。区域协调发展是我国经济可持续发展的内在要求，“一带一路”建设、京津冀协同发展、长江经济带发展是我国推动区域协调发展的主要抓手。“一带一路”建设着眼于统筹国内国际两个大局，充分利用国内国际两个市场、两种资源，全方位提高我国对外开放水平。京津冀协同发展的重点是疏解北京非首都功能、优化空间格局，高起点规划、高标准建设雄安新区。长江流域是我国重要的经济重心，也是沟通我国东中西部的交通大动脉。推动长江经济带发展将构建起沿江绿色生态走廊和综合立体交通走廊，促进沿江产业合理布局，更好发挥东部地区带动中西部地区发展的作用。目前，随着“一带一路”建设、京津冀协同发展、长江经济带发展深入推进，新经济增长点不断涌现，城市群建设提质增效，区域发展的协调性、均衡性大大增强。同世界分享中国发展机遇，促进共同繁荣。2008年国际金融危机爆发以来，以中国为代表的新兴市场经济体对全球经济增长的贡献率持续上升，并日益成为全球治理的重要力量。中国提出“一带一路”倡议，让世界分享中国发展机遇，推动经济全球化朝着更加开放、包容、普惠、平衡、共赢的方向发展，推动构建人类命运共同体；积极推动国际货币基金组织、世界银行的份额改革和治理机制改革，推动国际金融治理体系改革；积极参与上合组织、亚信论坛、中非合作论坛、中阿论坛，促进世界发展更加均衡。中国有信心、有能力保持经济中高速增长，继续在实现自身发展的同时为世界带来更多发展机遇、增添更多发展动力。因此，<strong>中国不仅自己能跨越“中等收入陷阱”，而且将助力其他发展中国家跨越“中等收入陷阱”。</strong>\",\n" +
            "                    \"url\": \"http://finance.sina.cn/china/gncj/2018-07-26/detail-ihfvkitw7337535.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://finance.sina.com.cn/china/gncj/2018-07-26/doc-ihfvkitw7337535.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"内马尔5人制玩花活被断 恼羞成怒把小球员放翻\",\n" +
            "                    \"time\": \"2018-07-26 08:04:00\",\n" +
            "                    \"src\": \"新浪体育\",\n" +
            "                    \"category\": \"sports\",\n" +
            "                    \"pic\": \"https://f.sinaimg.cn/sports/transform/489/w316h173/20180726/ExkQ-hfvkitw7595321.gif\",\n" +
            "                    \"content\": \"内马尔的世界杯早早结束，而现在的他正参加“内马尔五人制”比赛，在这样的比赛中，内马尔也一样较劲。这是一项被内马尔冠名的五人制赛事，汇集了来自全世界的五人制队伍。而内马尔的一次表现又让他成为了众矢之的。在一次一对一中，内马尔不断用双脚控制着足球，防守他的是一名未成年的球员。内马尔本来想凭借花活和技术晃过对手，但对方下脚很准，将球断掉。失去了球权的内马尔立刻进行反抢，在身位不占优的情况下他犯规将对方放倒在地。裁判马上鸣哨，内马尔起初还在示意自己的无辜，之后他露出了一个微笑。这一片段在网上也得到了传播，网友们对内马尔提出了批评，认为他的行为并不像一个职业运动员。《每日邮报》更是指责内马尔“卑鄙”，表示这并不是世界杯或者是欧冠赛场，这只是一场娱乐性的五人制比赛，但内马尔也不能控制好自己的情绪。（斧荆）\",\n" +
            "                    \"url\": \"http://sports.sina.cn/laliga/barcelona/2018-07-26/detail-ihfvkitw7607872.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://sports.sina.com.cn/g/laliga/2018-07-26/doc-ihfvkitw7607872.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"曝韦德来CBA打球传闻不靠谱 浙江目标另有其人\",\n" +
            "                    \"time\": \"2018-07-26 08:50:00\",\n" +
            "                    \"src\": \"新浪体育\",\n" +
            "                    \"category\": \"sports\",\n" +
            "                    \"pic\": \"\",\n" +
            "                    \"content\": \"北京时间7月26日，著名篮球记者、转会专家大卫-皮克透露，浙江队与韦德之间的“绯闻”或许并没有外界传说的那样靠谱。据他得到的消息，浙江队的小外援目标另有其人。大卫-皮克在推特上爆料称：“据我了解到的情况是，韦德到中国打球的传言，并不像宣传的那么靠谱。浙江队正在准备签下希腊帕纳西纳科斯队的外援马库斯-戴蒙，税后年薪为120万美元。”随后，大卫-皮克又在推特上透露，马库斯-戴蒙在帕纳辛奈科斯的的合同已经被浙江买断。“马库斯-戴蒙在帕纳西纳科斯的合同已经被浙江队买断了，2019-2020赛季他将重返希腊。”马库斯-戴蒙1990年3月20日出生，身高1米91，是一位后场双能卫。他在2012年第二轮第59顺位被马刺队选中，但却没有能得到在NBA打球的机会。他上赛季代表帕纳西纳科斯在欧冠联赛中出场28次，其中有8次首发，场均5.6分，0.3助攻和0.8个篮板。（豪斯）\",\n" +
            "                    \"url\": \"http://sports.sina.cn/cba/2018-07-26/detail-ihfvkitw7836595.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://sports.sina.com.cn/cba/2018-07-26/doc-ihfvkitw7836595.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"中国疾控中心：不合格百白破疫苗安全性指标符合标准\",\n" +
            "                    \"time\": \"2018-07-26 06:53:00\",\n" +
            "                    \"src\": \"中国食品药品监管\",\n" +
            "                    \"category\": \"finance\",\n" +
            "                    \"pic\": \"\",\n" +
            "                    \"content\": \"<span style=\\\"font-family: KaiTi_GB2312, KaiTi;\\\">疫苗科普1——中国疾病预防控制中心：效价指标不合格的百白破疫苗相关问题解答</span><strong>1．效价指标不合格的疫苗涉及哪些企业和批号？</strong>根据2017年11月3日国家食品药品监管总局发布信息：长春长生生物科技有限公司生产的批号为201605014-01、武汉生物制品研究所有限责任公司生产的批号为201607050-2的百白破疫苗效价指标不符合标准规定。<strong>2． 如何查询儿童是否接种了效价指标不合格的百白破疫苗？</strong>儿童家长或监护人可以查看儿童预防接种证上的百白破疫苗接种记录，与公布的疫苗生产企业和批号进行对照，判断是否接种了相应批号的不合格百白破疫苗。也可以咨询接种单位，由接种单位协助查询所接种百白破疫苗的批号，判断是否接种了相应批号的不合格百白破疫苗。还可以拨打12320卫生热线咨询。<strong>3． 接种了效价指标不合格的百白破疫苗会影响免疫效果吗？</strong><strong></strong>　该两批次百白破疫苗效价指标不合格，可能影响免疫保护效果。国家卫生计生委和河北、山东、重庆三省（市）卫生计生部门正在组织专家对该情况进行评估，根据评估结果将采取相应措施，妥善处理。<strong>4． 效价指标不合格的百白破疫苗安全吗？</strong>中国食品药品检定研究院对企业报请批签发的疫苗，逐批进行安全性指标检验，经查批签发记录，该两批次疫苗安全性指标符合标准。接种该两批次疫苗安全性风险没有增加。　<strong>5． 儿童接种过百白破疫苗还会患百日咳、白喉、破伤风吗？</strong>接种百白破疫苗是预防儿童白喉、破伤风和百日咳的有效措施。按照国家免疫程序，百白破疫苗需接种4剂次，分别于3、4、5月龄和18月龄各接种1剂次，完成4剂次接种的儿童可得到较好的保护效果。相关研究显示，百白破疫苗预防典型百日咳的效力约85%，因此，即使接种了百白破疫苗的儿童，少部分人也有可能罹患百日咳。百白破疫苗预防儿童破伤风的保护效力为80-100%，完成4剂次接种可为青少年期提供有效保护。百白破疫苗对白喉的保护效果较好，接种3剂次以上的疫苗保护效力约为95%。<strong>6． 我国百日咳、白喉和破伤风疾病发生情况如何？</strong>我国在20世纪60-70年代百日咳年发病率在100/10万~200/10万。自上世纪60年代开始接种百白破疫苗，1978年就将百白破疫苗纳入国家计划免疫，百白破疫苗普遍使用后，发病率大幅度下降。2008年以来，全国百日咳报告发病率控制在0.5/10万以下。我国除新生儿破伤风外，其他人群破伤风不属于法定报告传染病。2007年至今，全国无白喉病例报告。<strong>7． 百日咳、白喉和破伤风病人临床表现有哪些？</strong>百日咳是由百日咳杆菌引起的急性呼吸道传染病，传染源为百日咳病人，通过飞沫传播。临床症状为阵发性痉挛性咳嗽，咳后吸气有特殊“鸡鸣”样高亢声，咳嗽症状可持续2~3个月。本病多发生于＜5岁儿童，尤以＜6个月婴儿发病率较高。白喉是由白喉杆菌引起的急性呼吸道传染病。其临床特征为鼻、咽、喉等处黏膜充血、肿胀，并有灰白色假膜形成，导致呼吸障碍以及外毒素引起的中毒症状。破伤风是由破伤风杆菌引起的一种感染性疾病。破伤风杆菌常见于土壤中。皮肤创伤时，破伤风芽孢可被带入伤口，破伤风杆菌在厌氧环境下可产生破伤风毒素，引起破伤风特有的肌肉强直、阵发性痉挛为主的症状。在发生皮肤创伤时，尤其是存在上述感染因素时，应及时到医院就诊。医生会根据创伤情况，选择合理的治疗方案，必要时使用破伤风类毒素或/和破伤风免疫球蛋白进行暴露后预防。点击进入专题：<br/> 长生生物狂犬疫苗生产记录造假\",\n" +
            "                    \"url\": \"http://finance.sina.cn/chanjing/gdxw/2018-07-26/detail-ihfvkitw7409731.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://finance.sina.com.cn/chanjing/cyxw/2018-07-26/doc-ihfvkitw7409731.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"马英九动作频频 2020会复出挑落蔡英文么？\",\n" +
            "                    \"time\": \"2018-07-26 07:38:00\",\n" +
            "                    \"src\": \"环球网\",\n" +
            "                    \"category\": \"news\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/translate/547/w747h600/20180726/lCMr-hfvkitw7531661.jpg\",\n" +
            "                    \"content\": \"前“国安会秘书长”金溥聪：“马英九选不选，要问他本人”。鸿海董事长郭台铭大手笔捐助成立的“马英九基金会”将在本周五正式成立，也让前领导人马英九复出再选的传闻成为关注焦点。台湾最新一期《新新闻》杂志称，近半年来马英九人气回升，“马英九文教基金会”是其公共政策及宣扬理念的发声平台，日前他亲自声明“我虽不好战，但面对奉命起诉我的北检，我一定全力迎战”的表态，让外界清楚听到，他将在政治上再次奋起挑战蔡当局的弦外之音。连日来，马英九动作频频。据“联合新闻网”报道，他日前到高雄为国民党市长、市议员参选人站台，一连赶了十多个行程；在走访果贸市场时，市场摊商和买菜民众都争相与他握手、合影，很多人大喊“再出来参选”。台北市议员钟小平甚至爆料称，党内高层证实，马英九正筹备2020年“总统大选”。《新新闻》称，马英九在“三中案”遭起诉后强力回击，矛头直指绿营“政治追杀”，与他2007年因“首长特别费案”遭起诉后宣布参选的做法有异曲同工之妙，都是借危机凝聚挺马力量。前“国安会秘书长”金溥聪23日称，“马英九选不选，要问他本人”。他透露，马在尚未卸任前就有意效仿美国前总统卡特和奥巴马成立基金会，当初焦点是为维护南海主权。基金会董事廖元豪称，马英九成立基金会是希望有个“基地”阐述看法，成为引导台湾前进的力量。据台湾《联合报》报道，目前以马英九为首的基金会已有3个，其他两个是“新台湾人基金会”和“敦安基金会”。廖元豪称，这两个是马英九用竞选补助款成立的，属公益性质，而“马英九基金会”是直接以卸任领导人名义成立，马亲自担任董事长。《中国时报》称，从基金会董事名单来看，不少人曾是“内阁”成员。分析认为，蔡英文施政不得民心，马英九针砭当局的方向是对的，但是否要回锅参选还是要慎重考虑。还有分析直言，马英九卸任后人气不降反升，从侧面也反映出岛内民众对蔡英文当局的失望和不信任。\",\n" +
            "                    \"url\": \"http://news.sina.cn/gn/2018-07-26/detail-ihfvkitw7532049.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://news.sina.com.cn/c/2018-07-26/doc-ihfvkitw7532049.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"美军学中国造人工岛基地?防中俄高超声速导弹威胁\",\n" +
            "                    \"time\": \"2018-07-26 07:12:00\",\n" +
            "                    \"src\": \"澎湃新闻\",\n" +
            "                    \"category\": \"news\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/news/crawl/104/w550h354/20180726/L0k0-hfvkitw7469383.jpg\",\n" +
            "                    \"content\": \"为应对中俄高超声速导弹威胁，美军要建造“人工岛基地”？近日，美国某网站为美国海军大胆支招——既然拦截不了高超声速导弹，美国海军应该学中国造“人工岛基地”。对此，国防科技大学国防科技战略研究智库王群教授对科技日报记者表达了不同看法。他指出，“这一设想应该不是美军提出的军事战略，而是文章作者的观点，其出发点看似是为了应对所谓的高超声速武器的攻击，但命题本身就有问题。其目的更多的恐怕是为了吸引眼球、哗众取宠、制造混乱”。“高超声速武器当前还在发展过程中，即使像俄罗斯所说已经开始部署，能够突破现有任何反导系统，但还主要是针对陆地的固定目标，现在并不具备攻击航母这类大型海上移动目标的能力。比如，反舰弹道导弹能打击航母等大型舰船，不但自身有着高超的制导和导引水平，还必须要有比较完整的作战体系来提供保障，要在完善的信息支援系统支持下完成攻击。对于目前技术成熟度还不高的高超声速武器来说，想实施这样的攻击显然还不太现实。”王群指出。“应该看到，即使现在高超声速武器能够攻破大型舰船，不能机动的人工岛也逃不掉。人工岛地形一般比较简单，多是空旷的平地，没有太多的天然遮盖物，人员和装备很难隐蔽，而且周边海面也没有什么防护屏障，防御区域又比航母大很多，其防护手段和防御能力可能还不如航母编队，因此防御高超声速武器甚至是其他武器的攻击更难。这样看来，建造人工岛应对高超声速武器这个想法起不了应有作用。”他说。那么，美军会不会采用这样的战略呢？记者了解到，人工岛在世界各地存在已久。现代人工岛用途广泛，可用于兴建停泊大型船舶的深水港以及海上机场、大型电站或核电站，甚至是军事基地，被誉为“不沉的航母”。曾参与南海岛礁吹填的中国自航绞吸船“天鲸号”的造岛实力也引起美国的关注。如今，被称作“造岛神器”的自航绞吸船“天鲲号”也于2017年11月成功下水。新华社的报道指出，与大名鼎鼎的前辈“天鲸号”相比，“天鲲号”已全面超越，多项性能位居亚洲第一，技术世界领先。“天鲸号”每小时挖掘的海底混合物可填满一个标准足球场大、半米深的坑，用“削岩如泥”来形容毫不夸张。“从媒体报道上可以看出，美国一直反对中国在南海地区建造人工岛，如果他们自己也在敏感地区建造人工岛，有悖其一贯倡导的国际秩序理念，显然会引起国际社会的质疑，这将带来连锁反应，对周边局势造成不利影响。”王群也指出，“如果是为了全球战略考虑，不排除美军顶风建造人工岛。近年来，美军海外基地一直在不断收缩，已经不能很好地为其提供全球支援和保障，在关键地区建造人工岛作为保障和支援或前沿基地是有益的。”“当然，如果真如文章所说，美军打造一支超级疏浚船队，帮助盟国在南海等关键地区建造人工岛屿，难度还是相当大的。首先面对的就是经费问题。对于经费捉襟见肘的美军来说，这恐怕不能承受。另外，短期之内要想建造一支超级疏浚船队，在技术上也将面临很大挑战。目前疏浚船技术比较强的主要是比利时、荷兰、德国，我国的先进绞吸船也是引进消化外国技术建造的。美军如果再投入大量经费和精力去研制或引进绞吸船，不仅缺乏强烈需求，同时还要面对政治、外交、经费的掣肘，反而得不偿失。”王群指出。“这篇文章表面上看是为应对所谓高超声速武器的威胁，但是这个威胁目前并不存在。因此，更有可能是为美军未来全球布点做一些舆论铺垫，但这并非那么容易操作，也不太现实。”王群说。\",\n" +
            "                    \"url\": \"http://news.sina.cn/gj/2018-07-26/detail-ihfvkitw7478701.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://news.sina.com.cn/w/2018-07-26/doc-ihfvkitw7478701.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"一细节看出美航企多慌 再想想美方当初蛮横令人捧腹\",\n" +
            "                    \"time\": \"2018-07-26 07:24:00\",\n" +
            "                    \"src\": \"环球时报\",\n" +
            "                    \"category\": \"mil\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/mil/crawl/163/w550h413/20180726/e57e-hfvkitw7487122.jpg\",\n" +
            "                    \"content\": \"7月25日是中国民航局要求外国航空公司修改官网涉台错误标注的最后期限。截至目前，44家航空公司已全部对官网涉台名称作出修改。其中美国三大航空公司——美航、美联航、达美航空则在最后一天才在官网上把机场名称后面的“台湾”删除。但网友却在过程中发现了一件有趣的事……今天上午查询达美官网，它的状态还是这样的：台北的城市名后面标注了台湾。如今它的状态是这样的：台北城市名后面已经没有了台湾。但在这期间，有网友发现它曾有过这样的状态：只顾着删Taiwan，却忘了前面还有个逗号。难道说，因为限期已到，达美改得太急，连逗号都顾不上了？！这手忙脚乱的一幕，不禁让人想起今年5月初，中国民航局要求外国航空公司在网站和广告资料中修改对台港澳的称呼，不得将它们列为“国家”时，白宫发言人办公室那措辞极其蛮横的指责，称中国民航局的做法是“奥威尔式的胡说八道”，并且表示特朗普总统“将与美国人民站在一起抵御中共将中国式的政治正确强加给美国公司和美国公民”。结果现在把美国航企害成这狼狈样。早知如此，何必当初。英国《金融时报》6月初还曾引述知情人士的话称，美国官员已要求美联航、美国航空和达美航空不要听从中国的要求修改其网站对台湾的标注。但该人士表示，其实美国航空公司本身愿意在一段时间后遵守中国的要求。如今倒是印证了这一说法。附：7月25日外交部发言人就美国航空、达美航空修改对台湾的标识一事答记者问问：美国航空公司和达美航空公司今天都在其官网上更改了对台湾的标识，但并未像其他航空公司一样将台湾标为“台湾，中国”。中方对此是否满意？<strong>耿爽：我们注意到围绕此事取得的一些积极进展，对有关外国航空公司采取的整改举动给予肯定。</strong><strong>中方愿与外国企业分享中国的发展机遇，欢迎外国企业到中国投资兴业。同时，我们也希望他们在华经营时，遵守中国的法律法规，尊重中国的主权和领土完整，尊重中国人民的感情。当然，我们也会依法保障他们的合法权益，为其在华经营创造一个公平的环境。</strong>\",\n" +
            "                    \"url\": \"http://mil.sina.cn/zgjq/2018-07-26/detail-ihfvkitw7494866.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://mil.news.sina.com.cn/china/2018-07-26/doc-ihfvkitw7494866.shtml\"\n" +
            "                },\n" +
            "                {\n" + "   " +
            "                 \"title\": \"马英九动作频频 2020会复出挑落蔡英文么？\",\n" +
            "                    \"time\": \"2018-07-26 07:38:00\",\n" +
            "                    \"src\": \"环球网\",\n" +
            "                    \"category\": \"news\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/translate/547/w747h600/20180726/lCMr-hfvkitw7531661.jpg\",\n" +
            "                    \"content\": \"前“国安会秘书长”金溥聪：“马英九选不选，要问他本人”。鸿海董事长郭台铭大手笔捐助成立的“马英九基金会”将在本周五正式成立，也让前领导人马英九复出再选的传闻成为关注焦点。台湾最新一期《新新闻》杂志称，近半年来马英九人气回升，“马英九文教基金会”是其公共政策及宣扬理念的发声平台，日前他亲自声明“我虽不好战，但面对奉命起诉我的北检，我一定全力迎战”的表态，让外界清楚听到，他将在政治上再次奋起挑战蔡当局的弦外之音。连日来，马英九动作频频。据“联合新闻网”报道，他日前到高雄为国民党市长、市议员参选人站台，一连赶了十多个行程；在走访果贸市场时，市场摊商和买菜民众都争相与他握手、合影，很多人大喊“再出来参选”。台北市议员钟小平甚至爆料称，党内高层证实，马英九正筹备2020年“总统大选”。《新新闻》称，马英九在“三中案”遭起诉后强力回击，矛头直指绿营“政治追杀”，与他2007年因“首长特别费案”遭起诉后宣布参选的做法有异曲同工之妙，都是借危机凝聚挺马力量。前“国安会秘书长”金溥聪23日称，“马英九选不选，要问他本人”。他透露，马在尚未卸任前就有意效仿美国前总统卡特和奥巴马成立基金会，当初焦点是为维护南海主权。基金会董事廖元豪称，马英九成立基金会是希望有个“基地”阐述看法，成为引导台湾前进的力量。据台湾《联合报》报道，目前以马英九为首的基金会已有3个，其他两个是“新台湾人基金会”和“敦安基金会”。廖元豪称，这两个是马英九用竞选补助款成立的，属公益性质，而“马英九基金会”是直接以卸任领导人名义成立，马亲自担任董事长。《中国时报》称，从基金会董事名单来看，不少人曾是“内阁”成员。分析认为，蔡英文施政不得民心，马英九针砭当局的方向是对的，但是否要回锅参选还是要慎重考虑。还有分析直言，马英九卸任后人气不降反升，从侧面也反映出岛内民众对蔡英文当局的失望和不信任。\",\n" +
            "                    \"url\": \"http://news.sina.cn/gn/2018-07-26/detail-ihfvkitw7532049.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://news.sina.com.cn/c/2018-07-26/doc-ihfvkitw7532049.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"美军学中国造人工岛基地?防中俄高超声速导弹威胁\",\n" +
            "                    \"time\": \"2018-07-26 07:12:00\",\n" +
            "                    \"src\": \"澎湃新闻\",\n" +
            "                    \"category\": \"news\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/news/crawl/104/w550h354/20180726/L0k0-hfvkitw7469383.jpg\",\n" +
            "                    \"content\": \"为应对中俄高超声速导弹威胁，美军要建造“人工岛基地”？近日，美国某网站为美国海军大胆支招——既然拦截不了高超声速导弹，美国海军应该学中国造“人工岛基地”。对此，国防科技大学国防科技战略研究智库王群教授对科技日报记者表达了不同看法。他指出，“这一设想应该不是美军提出的军事战略，而是文章作者的观点，其出发点看似是为了应对所谓的高超声速武器的攻击，但命题本身就有问题。其目的更多的恐怕是为了吸引眼球、哗众取宠、制造混乱”。“高超声速武器当前还在发展过程中，即使像俄罗斯所说已经开始部署，能够突破现有任何反导系统，但还主要是针对陆地的固定目标，现在并不具备攻击航母这类大型海上移动目标的能力。比如，反舰弹道导弹能打击航母等大型舰船，不但自身有着高超的制导和导引水平，还必须要有比较完整的作战体系来提供保障，要在完善的信息支援系统支持下完成攻击。对于目前技术成熟度还不高的高超声速武器来说，想实施这样的攻击显然还不太现实。”王群指出。“应该看到，即使现在高超声速武器能够攻破大型舰船，不能机动的人工岛也逃不掉。人工岛地形一般比较简单，多是空旷的平地，没有太多的天然遮盖物，人员和装备很难隐蔽，而且周边海面也没有什么防护屏障，防御区域又比航母大很多，其防护手段和防御能力可能还不如航母编队，因此防御高超声速武器甚至是其他武器的攻击更难。这样看来，建造人工岛应对高超声速武器这个想法起不了应有作用。”他说。那么，美军会不会采用这样的战略呢？记者了解到，人工岛在世界各地存在已久。现代人工岛用途广泛，可用于兴建停泊大型船舶的深水港以及海上机场、大型电站或核电站，甚至是军事基地，被誉为“不沉的航母”。曾参与南海岛礁吹填的中国自航绞吸船“天鲸号”的造岛实力也引起美国的关注。如今，被称作“造岛神器”的自航绞吸船“天鲲号”也于2017年11月成功下水。新华社的报道指出，与大名鼎鼎的前辈“天鲸号”相比，“天鲲号”已全面超越，多项性能位居亚洲第一，技术世界领先。“天鲸号”每小时挖掘的海底混合物可填满一个标准足球场大、半米深的坑，用“削岩如泥”来形容毫不夸张。“从媒体报道上可以看出，美国一直反对中国在南海地区建造人工岛，如果他们自己也在敏感地区建造人工岛，有悖其一贯倡导的国际秩序理念，显然会引起国际社会的质疑，这将带来连锁反应，对周边局势造成不利影响。”王群也指出，“如果是为了全球战略考虑，不排除美军顶风建造人工岛。近年来，美军海外基地一直在不断收缩，已经不能很好地为其提供全球支援和保障，在关键地区建造人工岛作为保障和支援或前沿基地是有益的。”“当然，如果真如文章所说，美军打造一支超级疏浚船队，帮助盟国在南海等关键地区建造人工岛屿，难度还是相当大的。首先面对的就是经费问题。对于经费捉襟见肘的美军来说，这恐怕不能承受。另外，短期之内要想建造一支超级疏浚船队，在技术上也将面临很大挑战。目前疏浚船技术比较强的主要是比利时、荷兰、德国，我国的先进绞吸船也是引进消化外国技术建造的。美军如果再投入大量经费和精力去研制或引进绞吸船，不仅缺乏强烈需求，同时还要面对政治、外交、经费的掣肘，反而得不偿失。”王群指出。“这篇文章表面上看是为应对所谓高超声速武器的威胁，但是这个威胁目前并不存在。因此，更有可能是为美军未来全球布点做一些舆论铺垫，但这并非那么容易操作，也不太现实。”王群说。\",\n" +
            "                    \"url\": \"http://news.sina.cn/gj/2018-07-26/detail-ihfvkitw7478701.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://news.sina.com.cn/w/2018-07-26/doc-ihfvkitw7478701.shtml\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"title\": \"一细节看出美航企多慌 再想想美方当初蛮横令人捧腹\",\n" +
            "                    \"time\": \"2018-07-26 07:24:00\",\n" +
            "                    \"src\": \"环球时报\",\n" +
            "                    \"category\": \"mil\",\n" +
            "                    \"pic\": \"https://n.sinaimg.cn/mil/crawl/163/w550h413/20180726/e57e-hfvkitw7487122.jpg\",\n" +
            "                    \"content\": \"7月25日是中国民航局要求外国航空公司修改官网涉台错误标注的最后期限。截至目前，44家航空公司已全部对官网涉台名称作出修改。其中美国三大航空公司——美航、美联航、达美航空则在最后一天才在官网上把机场名称后面的“台湾”删除。但网友却在过程中发现了一件有趣的事……今天上午查询达美官网，它的状态还是这样的：台北的城市名后面标注了台湾。如今它的状态是这样的：台北城市名后面已经没有了台湾。但在这期间，有网友发现它曾有过这样的状态：只顾着删Taiwan，却忘了前面还有个逗号。难道说，因为限期已到，达美改得太急，连逗号都顾不上了？！这手忙脚乱的一幕，不禁让人想起今年5月初，中国民航局要求外国航空公司在网站和广告资料中修改对台港澳的称呼，不得将它们列为“国家”时，白宫发言人办公室那措辞极其蛮横的指责，称中国民航局的做法是“奥威尔式的胡说八道”，并且表示特朗普总统“将与美国人民站在一起抵御中共将中国式的政治正确强加给美国公司和美国公民”。结果现在把美国航企害成这狼狈样。早知如此，何必当初。英国《金融时报》6月初还曾引述知情人士的话称，美国官员已要求美联航、美国航空和达美航空不要听从中国的要求修改其网站对台湾的标注。但该人士表示，其实美国航空公司本身愿意在一段时间后遵守中国的要求。如今倒是印证了这一说法。附：7月25日外交部发言人就美国航空、达美航空修改对台湾的标识一事答记者问问：美国航空公司和达美航空公司今天都在其官网上更改了对台湾的标识，但并未像其他航空公司一样将台湾标为“台湾，中国”。中方对此是否满意？<strong>耿爽：我们注意到围绕此事取得的一些积极进展，对有关外国航空公司采取的整改举动给予肯定。</strong><strong>中方愿与外国企业分享中国的发展机遇，欢迎外国企业到中国投资兴业。同时，我们也希望他们在华经营时，遵守中国的法律法规，尊重中国的主权和领土完整，尊重中国人民的感情。当然，我们也会依法保障他们的合法权益，为其在华经营创造一个公平的环境。</strong>\",\n" +
            "                    \"url\": \"http://mil.sina.cn/zgjq/2018-07-26/detail-ihfvkitw7494866.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://mil.news.sina.com.cn/china/2018-07-26/doc-ihfvkitw7494866.shtml\"\n" +
            "                },\n" +
            "                 {\n" +
            "                    \"title\": \"鲁媒:小丁忘记了感恩 有如今价值鲁媒功不可没\",\n" +
            "                    \"time\": \"2018-07-25 14:26:00\",\n" +
            "                    \"src\": \"CBA综合\",\n" +
            "                    \"category\": \"sports\",\n" +
            "                    \"pic\": \"\",\n" +
            "                    \"content\": \"文章来源：山东商报丁彦雨航与NBA达拉斯独行侠已于日前签了一份训练营合同，他将代表独行侠参加季前赛，其中包括今年10月在上海、深圳举办的中国赛。去年夏天，丁彦雨航代表独行侠参加了夏季联赛，最终拒绝了独行侠提供的双向合同。述评看上去，丁彦雨航与NBA的距离更近了一步，可事实并非如此。所谓的训练营合同，实际上不属于NBA正式合同的范畴，没有任何保障。签下这样的合同之后，意味着你可以代表球队打季前赛，如果表现出色，就有机会跟球队签正式合同。NBA的正式合同种类繁多，非专业人士很难说得清楚。有一点可以肯定，去年同期，独行侠愿意给小丁提供一份双向合同，那份合同就属于NBA正式合同的一种。但是，小丁的需求更多，他想像周琦那样，成为一名有保障的NBA球员，双向合同满足不了他的要求，故而未能成行。时隔一年之后，小丁再次冲击NBA。按照常理，他跟独行侠已经有了感情基础，今夏代表独行侠打夏季联赛是最佳选择。然而，独行侠不这样想。他们在选秀大会上一口气搞来了4名新秀，要在夏季联赛上给这些新人足够的空间。基于此，小丁最终与篮网谈妥，代表篮网打夏季联赛。可不巧的是，他受伤了。医生诊断为右膝关节髌腱炎，关节腔积液，这种伤病不是硬伤，而是长期疲劳作战导致，需要静养。这样，他错过了夏季联赛，失去了一次展示自己的机会。说到伤病，就要提一下山东高速男篮。最近三个赛季，山东对小丁用得比较狠，垃圾时间也会让他在场上，而他又是那种搏命式的打法，以牺牲身体为代价，来换 取一场又一场的高得分。最终，小丁获得了两个常规赛本土球员MVP，外籍教练凯撒也得到了他想要的结果，可他的膝盖，已不堪重负。在这个节骨 眼上旧伤复发，小丁的心情肯定好不了，这就有了他后来“怒怼鲁媒”的故事。事实上，在丁彦雨航成长的道路上，山东主流媒体一向呵护有加，褒奖为主，间或来 点鞭策，都想让他变得更好。可以说，他能有如今的曝光率和商业价值，“鲁媒”功不可没。可是，在漫天的恭维声中，“丁神”似乎失去了平和的心态，也就忘记 了什么叫感恩。丁彦雨航与NBA的距离，看似很近，实则很远。在全世界范围内，他这种身高和技术特点的球员，一抓一大把，很多在CBA淘金的 外援，比如辽宁的哈德森、上海的弗雷戴特、广州的弗格等人，个人能力都在小丁之上，这些人都在NBA 得不到保障性合同，转而来到中国混饭吃。对于绝大多数美国球员来说，只要能在NBA混下去，就绝不会到海外打球。问题来了，就个人技术和市场 价值来说，周琦似乎不如丁彦雨航，为何他就能得到NBA正式合同？答案很简单，周琦的身体天赋太好了：他拥有2米18的身高，臂展达到了2米29，原地弹 跳80公分，体脂率只有4.85%。在全世界范围内，能跑能跳能投篮的高大中锋都是稀缺品，这就是周琦能进入NBA的原因。当然，每一个有梦想的人都值得尊重。机遇只留给有准备的人，只要坚持下去，持之以恒，丁彦雨航就有机会叩开NBA的大门。记者马宏观\",\n" +
            "                    \"url\": \"http://sports.sina.cn/cba/2018-07-25/detail-ihfvkitw3317828.d.html?vt=4&pos=108\",\n" +
            "                    \"weburl\": \"http://sports.sina.com.cn/cba/2018-07-25/doc-ihfvkitw3317828.shtml\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    public static void toastShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 凭pcscode 返回地图文件path
     *
     * @param activity
     * @param pcsCode  金诚区划code
     * @param type
     * @return
     */
    public static String getMapPathByPcscode(Activity activity, String pcsCode, String type) {
        if (null == activity ||
                pcsCode == null || pcsCode.isEmpty()) {
            return null;
        }

        String fileName = "";
        if ("sl".equals(type)) {
            fileName = "3302_sl.db"; //+"全大市在线矢量图"
        } else if ("sy".equals(type)) {
            fileName = pcsCode + "_sy.db"; //派出所机构业务id+"_sy.db"
        }
        //		String[] volumes = SDcardUtil.getVolumePaths(activity);
        //		String mapSaveDir = volumes[volumes.length-1]+"/ezmap22/";
        String mapSaveDir1 = Environment.getExternalStorageDirectory() + "/EzMap/";

        FileUtil.checkFolder(mapSaveDir1);
        String target = mapSaveDir1 + fileName;

        //实影离线，如未划分到派出所级别，使用分局级别
        File file = new File(target);

        if (!file.exists() && "sy".equals(type)) {
            String[] volumes = ExternalStorageUtil.getVolumePaths(activity);
            String ezmappath = volumes[volumes.length - 1] + "/EzMap/" + fileName;
            File tempfile = new File(ezmappath);
            if (tempfile.exists()) {
                return ezmappath;
            } else {
                fileName = pcsCode.substring(0, 6) + "_sy.db"; //县市区分局机构业务id+"_sy.db"
                target = mapSaveDir1 + fileName;
            }
        }
        return target;
    }

    public static void deleteImage(String path, Context context) {
        try {
            File file = new File(path);
            file.delete();

            String where = MediaStore.Images.Media.DATA + "='" + path + "'";
            context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, where, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void notifyMediaSync(String path, Context context) {
        try {
            String uriStr = "file://" + path;
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(uriStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示等待提示框
     */
    public static Dialog showWaitingDialog(String tip, Context context) {
        hideWaitingDialog();
        View view = View.inflate(context, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip)) {
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        }
        mDialogWaiting = new CustomDialog(context, view, R.style.MyDialog);
        mDialogWaiting.setCanceledOnTouchOutside(false);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(true);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public static void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }

    //二维码或者条形码的生成
    //生成二维码：
    //    qrCodeBitmap =createQRImageAll(ResponseUtils.getAmCodeResponse(storeId, reservationId), 400);
    public static Bitmap createQRImageAll(String content, int widthAndHeight) {
        qrCodeBitmap = null;
        qrCodeBitmap = EncodingHandler.createQRImageAll(content, widthAndHeight);
        return qrCodeBitmap;
    }
}
