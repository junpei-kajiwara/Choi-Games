"use strict"; //TODO 最後に削除

/**
 * 経過時間計測用タイマー
 */
var timer = NaN;

/**
 * ゲームを開始した時刻
 */
var startTime;

/**
 * 2枚選択後のインターバル用タイマー
 */
var flipTimer;

/**
 * ペアが一致した数
 */
var score = 0;

/**
 * 1枚目に選択したカード
 */
var prevCard;

/**
 * カードの総数
 */
const totalCards = 20;

/**
 * 行の総数
 */
const totalRows = 4;

/**
 * 列の総数
 */
const totalColums = 5;

/**
 * 配列の中身をシャッフルする。
 */
Array.prototype.shuffle = function() {
  var i = this.length;
  while (i) {
    var j = Math.floor(Math.random() * i);
    var tmp = this[--i];
    this[i] = this[j];
    this[j] = tmp;
  }
}

/**
 * ページが読み込まれた際、カードを配置し、カウントを開始する。
 */
window.onload = function() {
  var table = document.getElementById("table");

  // カードの数字を配列に格納 (ペア10組 合計20枚)
  var cards = new Array();
  for (var i = 1; i <= (totalCards / 2); i++) {
    cards.push(i);
    cards.push(i);
  }
  // カードをシャッフル
  cards.shuffle();

  // 各行
  for (var i = 0; i < totalRows; i++) {
    var tr = document.createElement("tr");

    // 各列
    for (var j = 0; j < totalColums; j++) {
      var td = document.createElement("td");
      td.className = "card back";

      // 表示するカードの数字
      td.number = cards[i * totalColums + j];

      td.onclick = flip;
      tr.appendChild(td);
    }
    table.appendChild(tr);
  }
  // カウント開始
  startTime = new Date();
  timer = setInterval(tick, 1000);
}

/**
 * 経過時間の計測を行う。
 * 現在時刻 - カウント開始時刻(ミリ秒)を計算し、
 * 秒単位に変換後、画面に表示する。
 */
function tick() {
  var now = new Date();
  var elapsed = Math.floor((now.getTime() - startTime.getTime()) / 1000);
  document.getElementById("time").textContent = elapsed;
}

/**
 * カードを表にする。
 * @param event
 */
function flip(event) {
  var target = event.target;

  // 2枚選択後のインターバルまたは、すでに表になったカードを選択した場合、何もしない。
  if (flipTimer || target.textContent != "") {
    return;
  }

  // カードを表示
  var num = target.number;
  target.className = "card";
  target.textContent = num;

  // 1枚目選択時、1枚目の数字を保持
  if (prevCard == null) {
    prevCard = target;
    return;
  }
  // 2枚目選択時、1枚目と数字が一致した場合、カードを表状態にし、scoreを加算する。
  if (prevCard.number == num) {
    // 数字が一致したペアが10組そろった場合、カウントを停止する。
    if (++score == 10) {
      clearInterval(timer);
    }
    prevCard = null;
    clearTimeout(flipTimer);
  } else {
    // 2枚目選択時、数字が一致しなかった場合、1秒後にカードを裏に戻す。
    flipTimer = setTimeout(function() {
      target.className = "card back";
      target.textContent = "";
      prevCard.className = "card back";
      prevCard.textContent = "";
      prevCard = null;
      flipTimer = NaN;
    }, 1000);
  }
}