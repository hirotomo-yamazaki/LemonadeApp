■PR概要
---
このアプリは、Androidに表示される画像をクリックすると

クリックされた際の状況に応じて、メッセージと画像が変更され、レモネードを生産できるというアプリです。

<br>
このアプリ制作で学習した点は、

***MainActivity.kt*** ：　findViewById(), setOnClickListener(), setContentView()
- これらメソッドを使ってアプリケーションの処理の流れを全体的に把握することができました。
- 一つ一つのメソッドの働きを理解することで、コードを読みながら処理がイメージでき、制作がスムーズに行えました。


***activity_main.xml*** ： TextView, ImageView, ConstraintLayout, LinearLayout, match_parent, wrap_parent

- 各View要素をConstraintLayoutで配置を決定することを学び、思うように配置極めができるようになりました。
- LinearLayoutはView要素をグループ化して、扱っていくのだと学習しました。

<br>

■アプリ作成時参考リンク
---
[setImageResource()について](https://codeforfun.jp/android-studio-how-to-change-image-of-imageview-dynamically/)

[xmlの概要について](https://qiita.com/mii-chang/items/ee965c1e8826d4e59414)

[ConstraintLayoutについて](https://qiita.com/hoshiume11/items/6cbcce32b58292715ad7)

[xmlの詳細、各要素の記述方法について](https://mixi-developers.mixi.co.jp/22-technical-training-5fc362a9dc41#9b26)



■コーディングを行ったファイル
---
[MainActivity.kt](https://github.com/hirotomo-yamazaki/LemonadeApp/blob/0d3b1438bdec45054b6003e484141a0ab8a20dd8/app/src/main/java/com/example/lemonade/MainActivity.kt)

[activity_main.xml](https://github.com/hirotomo-yamazaki/Lemonade/blob/3615a0ac154dde2c9ea14cb677d696973b694fb9/app/src/main/res/layout/activity_main.xml)

こちらのXMLファイルは実装はしていませんが、LinearLayoutを使用して、レイアウトを作成してみました。

[linearlayout.xml](https://github.com/hirotomo-yamazaki/Lemonade/blob/d16543d40978eca1512a36fc4fccf5a44c9622cc/app/src/main/res/layout/linearlayout.xml)



■動作確認内容(アプリとして動作に問題ないか、Testファイルで etc…..)
---

Project: Lemonade App - Starter Code
==================================

Starter code for the first independent project for [Android Basics in Kotlin](https://developer.android.com/courses/android-basics-kotlin/course)

Introduction
------------

This is the starter code for the Lemonade app project in the [final pathway](https://developer.android.com/courses/pathways/android-basics-kotlin-four) of Android Basics [Unit 1](https://developer.android.com/courses/android-basics-kotlin/unit-1). This project is an opportunity for you to demonstrate the concepts you learned in the unit.

Pre-requisites
--------------

- Complete [Unit 1](https://developer.android.com/courses/android-basics-kotlin/unit-1) of Android Basics in Kotlin

Getting Started
---------------

1. Download the starter code
2. Open the project in Android Studio
3. Complete the project in accordance with the [project instructions](https://developer.android.com/codelabs/basic-android-kotlin-training-project-lemonade)

Tips
----

- Use the provided tests to ensure your app is running as expected
- DO NOT ALTER THE PROVIDED TESTS
