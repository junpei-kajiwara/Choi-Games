"use strict"; //TODO 最後に削除

/**
 * ステージのデータ
 */
var stageData = [
  [6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 6, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 6, 6, 2, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 6, 6, 0, 0, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 0, 0, 2, 0, 0, 2, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 0, 6, 0, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 0, 0, 0, 6, 0, 6, 6, 6, 0, 6, 6, 6, 6, 0, 0, 1, 1, 6, 6],
  [6, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 6, 6],
  [6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 0, 6, 0, 6, 0, 0, 1, 1, 6, 6],
  [6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6],
  [6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6]
];

/**
 * 描画する為のグラフィックコンテキスト
 */
var graphicContext;

/**
 * 主人公のX座標
 */
var positionX = 12;

/**
 * 主人公のY座標
 */
var positionY = 8;

/**
 * 初期化関数
 */
window.onload = function() {
  graphicContext = document.getElementById("soko").getContext("2d");
  onkeydown = mykeydown;
  repaint();
}

// 上移動
function moveUp(){
  mykeydown({
    keyCode:38
  });
}

// 下移動
function moveDown(){
  mykeydown({
    keyCode:40
  });
}

// 左移動
function moveLeft(){
  mykeydown({
    keyCode:37
  });
}

// 右移動
function moveRight(){
  mykeydown({
    keyCode:39
  });
}

function mykeydown(e) {
  var dx0 = positionX, dx1 = positionX, dy0 = positionY, dy1 = positionY;
  switch (e.keyCode) {
      case 37: dx0--; dx1-=2;
          break;
      case 38: dy0--; dy1-=2;
          break;
      case 39: dx0++; dx1+=2;
          break;
      case 40: dy0++; dy1+=2;
          break;
  }

  if ((stageData[dy0][dx0] & 0x2) == 0) {  // 荷物なし＆壁なし→進む
      positionX = dx0;
      positionY = dy0;
  } else if ((stageData[dy0][dx0] & 0x6) == 2) { // 進行方向に荷物あり
      if ((stageData[dy1][dx1] & 0x2) == 0) {  // 荷物なし＆壁なし→進む
          stageData[dy0][dx0] ^= 2;    // 隣の荷物をクリア
          stageData[dy1][dx1] |= 2;    // 更に先に荷物をセット
          positionX = dx0;
          positionY = dy0;
      }
  }
  repaint();
}

function repaint() {
  graphicContext.fillStyle = "black";
  graphicContext.fillRect(0, 0, 800, 440);

  for (var y = 0 ; y < stageData.length ; y++) {
      for (var x = 0 ; x < stageData[y].length ; x++) {
          if (stageData[y][x] & 0x1) {
              graphicContext.drawImage(imgGoal, x * 40, y * 40, 40, 40);
          }
          if (stageData[y][x] & 0x2) {
              graphicContext.drawImage(imgLuggage, x * 40, y * 40, 40, 40);
          }
          if (stageData[y][x] == 6) {
              graphicContext.drawImage(imgWall, x * 40, y * 40, 40, 40);
          }
      }
  }
  graphicContext.drawImage(imgWorker, positionX * 40, positionY * 40, 40, 40);
}