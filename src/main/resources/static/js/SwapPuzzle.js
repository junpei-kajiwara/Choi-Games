"use strict"; //TODO 最後に削除

/**
 * タイル情報格納配列
 */
var tiles = new Array();

/**
 * 行の総数
 */
const totalRows = 4;

/**
 * 列の総数
 */
const totalColums = 4;

/**
 * シャッフルの回数
 */
const shuffleCounts = 1000;

/**
 * ページが読み込まれた際、タイルの表示を行う。
 */
window.onload = function() {
  var table = document.getElementById("table");

  // 各行
  for (var i = 0; i < totalRows; i++) {
    var tr = document.createElement("tr");

    // 各列
    for (var j = 0; j < totalColums; j++) {
      var td = document.createElement("td");
      var index = i * totalColums + j;
      td.className = "tile";

      // タイルの並び順を設定
      td.index = index;

      // タイルに表示する数字を設定 (0の場合は空文字を設定)
      td.value = index;
      td.textContent = td.value == 0 ? "" : td.value;

      // タイルにイベントを設定し、配列に格納
      td.onclick = click;
      tr.appendChild(td);
      tiles.push(td);
    }
    table.appendChild(tr);
  }
  // click()を繰り返しコールし、初回シャッフルを行う。
  for (var i = 0; i < shuffleCounts; i++) {
    click({
      target: {
        index: Math.floor(Math.random() * (totalRows * totalColums)),
        firstShuffle: true
      }
    });
  }
}

/**
 * クリックされたタイルの上下左右いずれかに空のタイルが存在する場合、swap()をコールする。
 * @param event  // クリックされたタイル
 */
function click(event) {
  var index = event.target.index;

  // indexから上隣のタイルのindex
  var upTileIndex = index - totalColums;

  // indexから下隣のタイルのindex
  var downTileIndex = index + totalColums;

  // indexから左隣のタイルのindex
  var leftTileIndex = index - 1;

  // indexから右隣のタイルのindex
  var rightTileIndex = index + 1;

  // 入れ替え先にタイルが存在するかつ、隣のタイルの値が空の場合、タイルの入れ替えを行う。
  if (index - totalColums >= 0 && tiles[upTileIndex].value == 0) {
    swap(index, upTileIndex);
  } else if (index + totalColums < (totalColums * totalRows) && tiles[downTileIndex].value == 0) {
    swap(index, downTileIndex);
  } else if (index % totalColums != 0 && tiles[leftTileIndex].value == 0) {
    swap(index, leftTileIndex);
  } else if (index % totalColums != totalColums - 1 && tiles[rightTileIndex].value == 0) {
    swap(index, rightTileIndex);
  }
  // 初回シャッフル時はゲーム終了判定を実施しない。
  if (!event.target.firstShuffle) {
    doJudge();
  }
}

/**
 * 引数に指定された並び順のタイルを入れ替える。
 * 入れ替えはタイルの値(value)のみを入れ替えし、並び順(index)の入れ替えは行わない。
 *
 * @param srcTileIndex  //入れ替え元のタイルのindex
 * @param destTileIndex  //入れ替え先のタイルのindex
 */
function swap(srcTileIndex, destTileIndex) {
  var tmp = tiles[srcTileIndex].value;
  tiles[srcTileIndex].textContent = tiles[destTileIndex].textContent;
  tiles[srcTileIndex].value = tiles[destTileIndex].value;
  tiles[destTileIndex].textContent = tmp;
  tiles[destTileIndex].value = tmp;
}

/**
 * ゲーム終了の判定処理を行う。
 */
function doJudge() {
  var gameOverFlg = true;
  for (var i = 0; i < tiles.length; i++) {
    if (tiles[i].index != tiles[i].value) {
      gameOverFlg = false;
    }
  }
  if (gameOverFlg) {
    document.getElementById("gameOver").textContent = "ゲームクリア！！";
  }
}