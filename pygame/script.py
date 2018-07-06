#!/usr/bin/env python
# -*- coding: utf-8 -*-
import pygame
from pygame.locals import *
import sys

SCREEN_SIZE = (640, 480)  # 画面サイズ

# Pygameを初期化
pygame.init()
# SCREEN_SIZEの画面を作成
screen = pygame.display.set_mode(SCREEN_SIZE)
# タイトルバーの文字列をセット
pygame.display.set_caption(u"ウィンドウの作成")
# 画像を読み込む
img = pygame.image.load('mon_191.bmp')

# ゲームループ
while True:
    screen.fill((0,0,255))   # 画面を青色で塗りつぶす

    screen.blit(img, (0, 0))    # 画面の左上に画像を描画する

    pygame.display.update()  # 画面を更新
    # イベント処理
    for event in pygame.event.get():
        if event.type == QUIT:  # 終了イベント
            sys.exit()
