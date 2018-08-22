package com.example.hp.demo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DeptBean {

    /**
     * returncode : null
     * returnstr : null
     * count : 72
     * list : [{"id":"d8d7828e-efa6-4ffa-b194-d21d7e43c3b2","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203010000","depname":"浙江省宁波市公安局海曙分局国内安全保卫大队","depshort":"海曙分局国保大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020301","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"5d81aad6-f28a-46a9-a823-fe6eb5614577","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203020000","depname":"浙江省宁波市公安局海曙分局经济犯罪侦查大队","depshort":"海曙分局经侦大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020302","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203030000","depname":"浙江省宁波市公安局海曙分局治安大队","depshort":"海曙分局治安大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020303","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"51d7b899-e736-41b0-ab2b-93f44ee5a31b","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030100","depname":"浙江省宁波市公安局海曙分局治安大队经文保中队","depshort":"海曙分局治安大队经文保中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030301","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"9b29e1b1-c215-4cfa-a33c-d5cf574d31f6","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030200","depname":"浙江省宁波市公安局海曙分局治安大队行动中队","depshort":"海曙分局治安大队行动中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030302","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"6f41c7ac-b2a5-4541-83f2-f1c67872c926","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030300","depname":"浙江省宁波市公安局海曙分局治安大队基础管理中队","depshort":"海曙分局治安大队基础管理中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030303","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"56f26770-4cd3-4e6f-add1-c68ce35cc99f","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030400","depname":"浙江省宁波市公安局海曙分局户证中心","depshort":"海曙分局户证中心","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030304","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"5b0d831e-220a-433d-a1bd-19d8493f0b7a","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030500","depname":"浙江省宁波市公安局海曙分局治安大队保安管理中队","depshort":"海曙分局治安大队保安管理中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030305","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"b28d1108-574e-4d44-86e2-8832ccb21c12","pid":"17d07f6b-1b0e-45e7-bc86-fb43ac34612f","depcode":"330203030600","depname":"浙江省宁波市公安局海曙分局治安大队综合室","depshort":"海曙分局治安大队综合室","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030306","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"99feed64-018e-4fc9-91f0-98216b673d95","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203050000","depname":"浙江省宁波市公安局海曙分局刑事侦查大队","depshort":"海曙分局刑侦大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533189000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020305","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"26f3fa0c-b068-4654-acf7-7a9f62e59941","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050400","depname":"浙江省宁波市公安局海曙分局刑事侦查大队重案中队","depshort":"海曙分局刑侦大队重案中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030504","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"995ca548-bdfc-4164-8ed9-1885527cf54d","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050500","depname":"浙江省宁波市公安局海曙分局刑事侦查大队技术中队","depshort":"海曙分局刑侦大队技术中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030505","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"c3516161-cdf5-43d4-a64e-f8e279f9f866","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050600","depname":"浙江省宁波市公安局海曙分局刑事侦查大队盗抢犯罪情报研判中队","depshort":"海曙分局刑侦大队盗抢犯罪情报研判中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030506","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"49de9ebe-9f95-48fd-98d9-7f4649b378bc","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050700","depname":"浙江省宁波市公安局海曙分局刑事侦查大队信息中队","depshort":"海曙分局刑事侦查大队信息中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030507","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"2dc726fa-5982-422e-965b-904d2a07dab6","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050800","depname":"浙江省宁波市公安局海曙分局刑事侦查大队综合室","depshort":"海曙分局刑侦大队综合室","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030508","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"55e08ccd-f250-41d2-a101-6223a492e10b","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203050900","depname":"浙江省宁波市公安局海曙分局刑事侦查大队办案中队","depshort":"海曙分局刑事侦查大队办案中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030509","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"20eb1fde-8962-42d8-b9f2-bbdb39af9092","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203051100","depname":"浙江省宁波市公安局海曙分局刑事侦查大队街面案件侦查中队","depshort":"海曙分局刑侦大队街面案件侦查中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030511","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"49e56794-8935-4782-beb4-ffc3c05b2b43","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203051200","depname":"浙江省宁波市公安局海曙分局刑事侦查大队系列性案件侦查中队","depshort":"海曙分局刑侦大队系列性案件侦查中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030512","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"befbc826-87d7-4f82-ba66-cf455b2ccf84","pid":"99feed64-018e-4fc9-91f0-98216b673d95","depcode":"330203059700","depname":"浙江省宁波市公安局海曙分局刑事侦查大队有组织犯罪侦查中队","depshort":"海曙分局刑侦大队有组织犯罪侦查中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302030597","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"015b2abf-b460-4fc5-8e67-941c1bcf669e","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203110000","depname":"浙江省宁波市公安局海曙分局网络警察大队","depshort":"海曙分局网警大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020311","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"a2e44c98-a346-4e0a-a1f0-038e6666cf7f","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203180000","depname":"浙江省宁波市公安局海曙分局法制大队","depshort":"海曙分局法制大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020318","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"9ddc1676-f69a-40a7-94d6-4de2b7a71f24","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203210000","depname":"浙江省宁波市公安局海曙分局禁毒大队","depshort":"海曙分局禁毒大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020321","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"e9be99c1-8954-4c7e-bb74-732feeb6cb6e","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203220000","depname":"浙江省宁波市公安局海曙分局信通科技科","depshort":"海曙分局信通科技科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020322","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"7f2620a1-5230-4dc4-8307-acf6dca6d1ed","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203230000","depname":"浙江省宁波市公安局海曙分局后勤科","depshort":"海曙分局后勤科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020323","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"713327ab-fa36-43b5-8253-5bd211d9a7ce","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203250000","depname":"浙江省宁波市公安局海曙分局警卫科","depshort":"海曙分局警卫科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020325","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"f943c0f8-bb26-4491-a547-59c4f90cedd2","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203260000","depname":"浙江省宁波市公安局海曙分局协辅警管理科","depshort":"海曙分局协辅警管理科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020326","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"639d5614-d12a-42c1-b9cb-9075291601ca","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203270000","depname":"浙江省宁波市公安局海曙分局行政执法保障大队","depshort":"海曙分局行政执法保障大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020327","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"c33ed1c4-7b2e-454d-a0fd-9f5d9569eca5","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203310000","depname":"浙江省宁波市公安局海曙分局办公室","depshort":"海曙分局办公室","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020331","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"cb708f0a-5527-4ed7-8429-f067cc58bafb","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203320000","depname":"浙江省宁波市公安局海曙分局纪委","depshort":"海曙分局纪委","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020332","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"be21c56e-39bc-4cd5-a94c-e6fac317ef69","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203340000","depname":"浙江省宁波市公安局海曙分局警务督察大队","depshort":"海曙分局督察大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020334","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"e9e00865-0acc-4bfd-b689-2a4ff0f5b467","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203350000","depname":"浙江省宁波市公安局海曙分局政治处","depshort":"海曙分局政治处","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020335","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"ee524b4d-54eb-439a-a1db-4c47163595d0","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203360000","depname":"浙江省宁波市公安局海曙分局110指挥中心","depshort":"海曙分局110指挥中心","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533190000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020336","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"1e972603-4b18-4059-97fb-da43f99d7da8","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203370000","depname":"浙江省宁波市公安局海曙分局情报科","depshort":"海曙分局情报科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020337","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"a44ed62d-7e74-48a0-9854-fc6b33ce90aa","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203380000","depname":"浙江省宁波市公安局海曙分局反恐大队","depshort":"海曙分局反恐大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020338","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"1ffdc2bc-232d-4600-8eeb-eee3bfeef27b","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203390000","depname":"浙江省宁波市公安局海曙分局出入境管理科","depshort":"海曙分局出入境管理科","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020339","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"9587cf56-bb72-4350-a8d4-1eaa45110490","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203460000","depname":"浙江省宁波市公安局海曙分局巡（特）警大队","depshort":"海曙分局巡（特）警大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020346","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"6887ccda-7b01-481c-a86c-7ce4d5310838","pid":"9587cf56-bb72-4350-a8d4-1eaa45110490","depcode":"330203461100","depname":"浙江省宁波市公安局海曙分局巡（特）警大队二中队","depshort":"海曙分局巡（特）警大队二中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302034611","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"aba96add-130d-4848-839c-5eb3125ef968","pid":"9587cf56-bb72-4350-a8d4-1eaa45110490","depcode":"330203461200","depname":"浙江省宁波市公安局海曙分局巡（特）警大队三中队","depshort":"海曙分局巡（特）警大队三中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302034612","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"e6b47377-1833-4546-85db-c2a80ec3ef21","pid":"9587cf56-bb72-4350-a8d4-1eaa45110490","depcode":"330203461300","depname":"浙江省宁波市公安局海曙分局巡（特）警大队一中队","depshort":"海曙分局巡（特）警大队一中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302034613","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"9ac30d8a-f8a6-45e0-9046-151d73bc69f9","pid":"9587cf56-bb72-4350-a8d4-1eaa45110490","depcode":"330203461500","depname":"浙江省宁波市公安局海曙分局巡（特）警大队综合室","depshort":"海曙分局巡（特）警大队综合室","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"3302034615","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"c59ac82f-0d5b-48cb-9d4f-14a51fd31add","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203480000","depname":"浙江省宁波市海曙区看守所","depshort":"海曙看守所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533203000,"modifier":"330204196811309010","modifydate":1492328661000,"depgrade":"3","pname":null,"shortCode":"33020348","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"8e4864ef-8965-47df-a9ab-06c3902d8299","pid":"c59ac82f-0d5b-48cb-9d4f-14a51fd31add","depcode":"330203481000","depname":"浙江省宁波市海曙区看守所一中队","depshort":"海曙看守所一中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533203000,"modifier":"330204196811309010","modifydate":1492328767000,"depgrade":"3","pname":null,"shortCode":"3302034810","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"bdc289f2-2321-45d1-9073-78fed2844669","pid":"c59ac82f-0d5b-48cb-9d4f-14a51fd31add","depcode":"330203482000","depname":"浙江省宁波市海曙区看守所二中队","depshort":"海曙看守所二中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533203000,"modifier":"330204196811309010","modifydate":1492328818000,"depgrade":"3","pname":null,"shortCode":"3302034820","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"e7e097ad-4e0f-4d02-b839-05560fe4cc55","pid":"c59ac82f-0d5b-48cb-9d4f-14a51fd31add","depcode":"330203483000","depname":"浙江省宁波市海曙区看守所三中队","depshort":"海曙看守所三中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533203000,"modifier":"330204196811309010","modifydate":1492328872000,"depgrade":"3","pname":null,"shortCode":"3302034830","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"c6a78f4c-36f5-47ca-89f0-1d2aed0caa83","pid":"c59ac82f-0d5b-48cb-9d4f-14a51fd31add","depcode":"330203484000","depname":"浙江省宁波市海曙区看守所四中队","depshort":"海曙看守所四中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533203000,"modifier":"330204196811309010","modifydate":1492328917000,"depgrade":"3","pname":null,"shortCode":"3302034840","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"ebc5e616-690c-4bec-929a-e234f1bce270","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203510000","depname":"浙江省宁波市公安局海曙分局江厦派出所","depshort":"海曙分局江厦派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020351","csize":0,"mainCode":"330203510000","mainShortCode":"33020351"},{"id":"8467b2d0-6e18-4ed5-adc0-f03a28a5187c","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203520000","depname":"浙江省宁波市公安局海曙分局月湖派出所","depshort":"海曙分局月湖派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020352","csize":0,"mainCode":"330203520000","mainShortCode":"33020352"},{"id":"72099108-d924-4dac-874d-4aff8625bc14","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203530000","depname":"浙江省宁波市公安局海曙分局南门派出所","depshort":"海曙分局南门派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020353","csize":0,"mainCode":"330203530000","mainShortCode":"33020353"},{"id":"140a56ec-0d9d-4fc6-a7b0-c131c7783891","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203540000","depname":"浙江省宁波市公安局海曙分局西门派出所","depshort":"海曙分局西门派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020354","csize":0,"mainCode":"330203540000","mainShortCode":"33020354"},{"id":"8f882c2a-3655-41c9-a418-4ca7efa25b73","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203550000","depname":"浙江省宁波市公安局海曙分局白云派出所","depshort":"海曙分局白云派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020355","csize":0,"mainCode":"330203550000","mainShortCode":"33020355"},{"id":"25996ce0-e40d-4a8c-ad67-7454f078d693","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203560000","depname":"浙江省宁波市公安局海曙分局望春派出所","depshort":"海曙分局望春派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020356","csize":0,"mainCode":"330203560000","mainShortCode":"33020356"},{"id":"dcd5522d-f793-4dd1-b12b-694ed7409990","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203570000","depname":"浙江省宁波市公安局海曙分局段塘派出所","depshort":"海曙分局段塘派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020357","csize":0,"mainCode":"330203570000","mainShortCode":"33020357"},{"id":"7a50a1c0-4f79-42bd-8af2-c915c9c84841","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203580000","depname":"浙江省宁波市公安局海曙分局广场派出所","depshort":"海曙分局广场派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020358","csize":0,"mainCode":"330203580000","mainShortCode":"33020358"},{"id":"03f0442b-2a07-40e6-8523-390240c3ba39","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203590000","depname":"浙江省宁波市公安局海曙分局鼓楼派出所","depshort":"海曙分局鼓楼派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020359","csize":0,"mainCode":"330203590000","mainShortCode":"33020359"},{"id":"d13f941b-9205-419e-9b66-3f3f8a733ade","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203610000","depname":"浙江省宁波市公安局海曙分局石碶派出所","depshort":"宁波市局海曙分局石碶派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489563798000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020361","csize":0,"mainCode":"330203610000","mainShortCode":"33020361"},{"id":"56b1126c-2892-4530-abd2-d08fae3940ff","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203620000","depname":"浙江省宁波市公安局海曙分局集士港派出所","depshort":"宁波市局海曙分局集士港派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489563810000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020362","csize":0,"mainCode":"330203620000","mainShortCode":"33020362"},{"id":"8f2255f1-5ae9-4b86-8007-4b3eca6cb790","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203630000","depname":"浙江省宁波市公安局海曙分局高桥派出所","depshort":"宁波市局海曙分局高桥派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564020000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020363","csize":0,"mainCode":"330203630000","mainShortCode":"33020363"},{"id":"54faf9b3-b87f-4b62-84d6-f8598832d0f9","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203640000","depname":"浙江省宁波市公安局海曙分局古林派出所","depshort":"宁波市局海曙分局古林派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564032000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020364","csize":0,"mainCode":"330203640000","mainShortCode":"33020364"},{"id":"dc21e2e3-4c0c-4c96-b03e-e0277e94c56e","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203650000","depname":"浙江省宁波市公安局海曙分局横街派出所","depshort":"宁波市局海曙分局横街派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564043000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020365","csize":0,"mainCode":"330203650000","mainShortCode":"33020365"},{"id":"264bece4-7b5d-4c80-8fbb-e285b6cc7aea","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203660000","depname":"浙江省宁波市公安局海曙分局鄞江派出所","depshort":"宁波市局海曙分局鄞江派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564056000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020366","csize":0,"mainCode":"330203660000","mainShortCode":"33020366"},{"id":"aaf0a8e3-77b0-4e5c-bd9e-4ebf4ffd2d3e","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203670000","depname":"浙江省宁波市公安局海曙分局洞桥派出所","depshort":"宁波市局海曙分局洞桥派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564068000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020367","csize":0,"mainCode":"330203670000","mainShortCode":"33020367"},{"id":"c1f98501-3fd4-48ce-85c7-53093e344530","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203680000","depname":"浙江省宁波市公安局海曙分局龙观派出所","depshort":"宁波市局海曙分局龙观派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564080000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020368","csize":0,"mainCode":"330203680000","mainShortCode":"33020368"},{"id":"9e518e00-0bbe-47eb-9642-1aedecbc6ae9","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203690000","depname":"浙江省宁波市公安局海曙分局章水派出所","depshort":"宁波市局海曙分局章水派出所","telephone":null,"address":null,"remark":null,"sort":"0","creator":"zladmin","creatdate":1489564092000,"modifier":null,"modifydate":null,"depgrade":"4","pname":null,"shortCode":"33020369","csize":0,"mainCode":"330203690000","mainShortCode":"33020369"},{"id":"21c6d62f-805a-48ef-87f2-00bca0335ea7","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203X00000","depname":"浙江省宁波市公安消防支队海曙区大队","depshort":"海曙大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X0","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"24035b47-d3b9-41e8-b2d7-a013944c26b1","pid":"21c6d62f-805a-48ef-87f2-00bca0335ea7","depcode":"330203X00100","depname":"浙江省宁波市公安消防支队海曙区大队海曙中队","depshort":"海曙中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X001","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"3f4a25a4-1bab-4641-add3-681a44ad9467","pid":"21c6d62f-805a-48ef-87f2-00bca0335ea7","depcode":"330203X00200","depname":"浙江省宁波市公安消防支队海曙区大队鄞奉路中队","depshort":"鄞奉路中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X002","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"aacdf193-8782-43e0-a5f0-03e42d937664","pid":"21c6d62f-805a-48ef-87f2-00bca0335ea7","depcode":"330203X00300","depname":"浙江省宁波市公安消防支队海曙区大队天一中队","depshort":"天一中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X003","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"3da7e9a2-ea00-4b41-9579-ff8e05318ebf","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203X10000","depname":"浙江省市公安消防支队特勤大队","depshort":"特勤大队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X1","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"bfee6c7f-db4e-4219-b5d7-e2a82a9bb738","pid":"3da7e9a2-ea00-4b41-9579-ff8e05318ebf","depcode":"330203X10100","depname":"浙江省宁波市公安消防支队特勤大队特勤一中队","depshort":"特勤一中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X101","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"92ea0f6b-e184-42bd-9025-69f025bd3017","pid":"3da7e9a2-ea00-4b41-9579-ff8e05318ebf","depcode":"330203X10200","depname":"浙江省宁波市公安消防支队特勤大队特勤二中队","depshort":"特勤二中队","telephone":null,"address":null,"remark":null,"sort":"0","creator":"sys","creatdate":1394533191000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"330203X102","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","pid":"1","depcode":"330203000000","depname":"浙江省宁波市公安局海曙分局","depshort":"海曙分局","telephone":null,"address":null,"remark":null,"sort":"255","creator":"sys","creatdate":1394533189000,"modifier":"330227198809275417","modifydate":1399945503000,"depgrade":"3","pname":null,"shortCode":"330203","csize":0,"mainCode":"330203000000","mainShortCode":"330203"},{"id":"e8bd9a7a-dbf4-4163-a9ae-d3a5a19fa9e6","pid":"444d3aa1-fcc7-4952-bab3-5bbdb745f665","depcode":"330203490000","depname":"拘留所","depshort":"海曙分局拘留所","telephone":null,"address":null,"remark":null,"sort":null,"creator":"330226198111150472","creatdate":1492592097000,"modifier":null,"modifydate":null,"depgrade":"3","pname":null,"shortCode":"33020349","csize":0,"mainCode":"330203000000","mainShortCode":"330203"}]
     */

    private Object returncode;
    private Object returnstr;
    private int count;
    private List<ListBean> list;

    public Object getReturncode() {
        return returncode;
    }

    public void setReturncode(Object returncode) {
        this.returncode = returncode;
    }

    public Object getReturnstr() {
        return returnstr;
    }

    public void setReturnstr(Object returnstr) {
        this.returnstr = returnstr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : d8d7828e-efa6-4ffa-b194-d21d7e43c3b2
         * pid : 444d3aa1-fcc7-4952-bab3-5bbdb745f665
         * depcode : 330203010000
         * depname : 浙江省宁波市公安局海曙分局国内安全保卫大队
         * depshort : 海曙分局国保大队
         * telephone : null
         * address : null
         * remark : null
         * sort : 0
         * creator : sys
         * creatdate : 1394533189000
         * modifier : null
         * modifydate : null
         * depgrade : 3
         * pname : null
         * shortCode : 33020301
         * csize : 0
         * mainCode : 330203000000
         * mainShortCode : 330203
         */

        private String id;
        private String pid;
        private String depcode;
        private String depname;
        private String depshort;
        private Object telephone;
        private Object address;
        private Object remark;
        private String sort;
        private String creator;
        private long creatdate;
        private Object modifier;
        private Object modifydate;
        private String depgrade;
        private Object pname;
        private String shortCode;
        private int csize;
        private String mainCode;
        private String mainShortCode;


        private TreeNodes parent;
        private List<TreeNodes> children = new ArrayList<TreeNodes>();


        public TreeNodes getParent() {
            return parent;
        }

        public void setParent(TreeNodes parent) {
            this.parent = parent;
        }

        public List<TreeNodes> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNodes> children) {
            this.children = children;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getDepcode() {
            return depcode;
        }

        public void setDepcode(String depcode) {
            this.depcode = depcode;
        }

        public String getDepname() {
            return depname;
        }

        public void setDepname(String depname) {
            this.depname = depname;
        }

        public String getDepshort() {
            return depshort;
        }

        public void setDepshort(String depshort) {
            this.depshort = depshort;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public long getCreatdate() {
            return creatdate;
        }

        public void setCreatdate(long creatdate) {
            this.creatdate = creatdate;
        }

        public Object getModifier() {
            return modifier;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public Object getModifydate() {
            return modifydate;
        }

        public void setModifydate(Object modifydate) {
            this.modifydate = modifydate;
        }

        public String getDepgrade() {
            return depgrade;
        }

        public void setDepgrade(String depgrade) {
            this.depgrade = depgrade;
        }

        public Object getPname() {
            return pname;
        }

        public void setPname(Object pname) {
            this.pname = pname;
        }

        public String getShortCode() {
            return shortCode;
        }

        public void setShortCode(String shortCode) {
            this.shortCode = shortCode;
        }

        public int getCsize() {
            return csize;
        }

        public void setCsize(int csize) {
            this.csize = csize;
        }

        public String getMainCode() {
            return mainCode;
        }

        public void setMainCode(String mainCode) {
            this.mainCode = mainCode;
        }

        public String getMainShortCode() {
            return mainShortCode;
        }

        public void setMainShortCode(String mainShortCode) {
            this.mainShortCode = mainShortCode;
        }
    }
}
