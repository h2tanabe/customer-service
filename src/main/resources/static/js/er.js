$.jCanvas.defaults.fromCenter = false;
$.jCanvas.defaults.layer = true;
var columName1 = ["opportunities", "name [varchar(50)]", "ammount [bigint]", "status [varchar(5)]", "account_id [bigint]"];
var columName2 = ["customers", "id[int]", "name[varchar(50)]", "name_kana[varchar(50)]", "postal_code[char(8)]", "address[varchar(150)]", "mail[varchar(100)]", "gender[char(1)]", "birth_date[date]", "tel[varchar(50)]"];
var columName3 = ["users", "user_name [varchar(100)]", "encoded_password [varchar(100)]"];
var columName4 = ["address", "old_zip[varchar(5)]", "ken_cd[int]", "ken_furi[varchar]", "city_furi[varchar]", "town_furi[varchar]", "ken_name[varchar]", "city_name[varchar]"];
createTable("g1", 50, 50, columName1, 'red');
createTable("g2", 300, 50, columName2, 'red');
createTable("g3", 100, 300, columName3, 'blue');
createTable("g4", 200, 400, columName4, 'blue');
// エンティティの線を引く
drawCurveLink("g1", "g2");
// ERのテーブル作成
// 引数 Canvasのグループ名、ヨコの距離（X軸）、テタの距離（Y軸）、コラム名
function createTable(tableName, xl, yl, columName, color) {
  let xtext = xl;
  let ytext = yl;
  let xsquare = xl - 8; // テキストラインより下げる（インデント）
  let ysquare = yl; // 高さは合わせる
  let borderWidth = 0;
  let borderHeight = 0;
  let boxColor = color;
  // 線・四角形・テキストをグループ化
  // テキスト記載
  function drawText(text, x, y, name, tableName) {
    $("canvas").drawText({
      fillStyle: "black",
      strokeStyle: "black",
      strokeWidth: "0.5",
      x: x,
      y: y,
      fontSize: 14,
      fontFamily: "Verdana, sans-serif",
      text: text,
      name: name + "-text",
      draggable: true, // 図形のドロップアンドドラック
      groups: [tableName],
      dragGroups: [tableName],
    });
  }
  // テキストを等間隔で記載（横軸固定、縦30間隔）
  for (var i = 0; i < columName.length; i++) {
    // コラム記載
    drawText(columName[i], xtext, ytext, columName[i], tableName);
    if (i == 0) {
      ytext = ytext + 30;
    } else {
      ytext = ytext + 20;
    }
  }
  // テキスト幅のMAXから四角形の幅調整
  // カラム数により高さ調整
  for (var i = 0; i < columName.length; i++) {
    borderWidth = Math.max(borderWidth, $("canvas").getLayer(columName[i] + "-text").width);
    borderHeight = (columName.length * 20.5) + 10;
  }
  // 四角形
  $("canvas").drawRect({
    strokeStyle: "blue",
    strokeWidth: 1,
    x: xsquare,
    y: ysquare,
    width: borderWidth + 15,
    height: borderHeight,
    draggable: true,
    groups: [tableName],
    dragGroups: [tableName],
    bringToFront: true,
    opacity: 0.2,
    fillStyle: boxColor,
    click: doClick,
    name: tableName,
    drag: onDrag,
    dragstop: onDragStop,
    dragcancel: onDragCancel
  });
  // テーブル名の下線
  $("canvas").drawLine({
    strokeStyle: "black",
    strokeWidth: 1,
    x1: xsquare,
    y1: ysquare + 20,
    x2: xsquare + borderWidth + 15,
    y2: ysquare + 20,
    draggable: true,
    groups: [tableName],
    dragGroups: [tableName]
  });
}
// 線を引き
function drawLink(entity1, entity2) {
  var border1 = $("canvas").getLayer(entity1);
  var border2 = $("canvas").getLayer(entity2);
  $("canvas").drawLine({
    strokeStyle: "black",
    strokeWidth: 1,
    draggable: false,
    x1: border1.x + border1.width,
    y1: border1.y + (border1.height / 2),
    x2: border2.x,
    y2: border2.y + (border2.height / 2), // Start/end point
    name: entity1 + "link" + entity2,
    groups: [entity1 + "link" + entity2],
    dragGroups: [entity1 + "link" + entity2],
    click: doClick,
  });
}
// 曲線
function drawCurveLink(entity1, entity2) {
  var border1 = $("canvas").getLayer(entity1);
  var border2 = $("canvas").getLayer(entity2);
  // 始点・終点
  var startPointX;
  var startPointY;
  var endPointX;
  var endPointY;
  var halfPointX;
  var halfPointY;
  var temp1;
  var temp2;
  var halfLengthX;
  var halfLengthY;
  var cpLine;
  var diffPoint;
  var diffHeight;
  cpLine = 100;
  diffPoint = 300; // 上下の差が離れたら始点・終点入れ替える
  // X軸：エンティティが逆転したら処理修正
  if (border1.x < border2.x) {
    startPointX = border1.x + border1.width;
    endPointX = border2.x;
  } else {
    startPointX = border2.x + border2.width;
    endPointX = border1.x;
  }
  // Y軸：エンティティが逆転したら処理修正
  if ((border1.y + (border1.height / 2)) < (border2.y + (border2.height / 2))) {
    startPointY = border1.y + (border1.height / 2);
    endPointY = border2.y + (border2.height / 2);
  } else {
    startPointY = border1.y + (border1.height / 2);
    endPointY = border2.y + (border2.height / 2);
  }
  // X軸：反対ならY軸入れ替えるエンティティが逆転したら処理修正
  if (border1.x > border2.x) {
    temp1 = startPointY;
    temp2 = endPointY;
    startPointY = temp2;
    endPointY = temp1;
    halfPointY = ((endPointY - startPointY) / 2) + startPointY;
  }
  // エンティティ下に行った場合
  diffHeight = border2.y - border1.y;
  if (diffHeight > diffPoint) {
    startPointX = border1.x + (border1.width / 2);
    endPointX = border2.x + (border2.width / 2);
    startPointY = border1.y + border1.height;
    endPointY = border2.y;
    cpLine = 50;
  }
  diffHeight = border1.y - border2.y;
  if (diffHeight > diffPoint) {
    startPointX = border2.x + (border2.width / 2);
    endPointX = border1.x + (border1.width / 2);
    startPointY = border2.y + border2.height;
    endPointY = border1.y;
    cpLine = 50;
  }
  halfPointX = ((endPointX - startPointX) / 2) + startPointX;
  halfPointY = ((endPointY - startPointY) / 2) + startPointY;
  halfLengthX = (endPointX - startPointX) / 2
  halfLengthY = (endPointY - startPointY) / 2
  // 距離が短いと曲線から直線に変換
  if (halfLengthX < (cpLine - 15)) {
    cpLine = 0;
  }
  $("canvas").drawBezier({
    strokeStyle: "black",
    strokeWidth: 1,
    draggable: false,
    x1: startPointX,
    y1: startPointY,
    cx1: halfPointX - cpLine, // 上に上がる
    cy1: halfPointY, //
    cx2: halfPointX - cpLine,
    cy2: halfPointY,
    x2: halfPointX,
    y2: halfPointY,
    cx3: halfPointX + cpLine,
    cy3: halfPointY,
    cx4: halfPointX + cpLine, //
    cy4: halfPointY, //
    x3: endPointX,
    y3: endPointY,
    name: entity1 + "link" + entity2,
    groups: [entity1 + "link" + entity2],
    dragGroups: [entity1 + "link" + entity2],
    click: doClick,
  });
}
// ドラックアンドドロップすると線を再定義する
function onDrag(layer) {
  var linkname = "/link/gi";
  $("canvas").removeLayerGroup(/link/gi);
  drawCurveLink("g1", "g2");
}

function onDragStop(layer) {}

function onDragCancel(layer) {}

function doClick(layer) {
  var getLayer = $("canvas").getLayer(layer);
}
// クリックイベント
function doDoubleClick(layer) {
  $("canvas").removeLayerGroup(layer.name);
}