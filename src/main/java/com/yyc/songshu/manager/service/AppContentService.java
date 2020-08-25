package com.yyc.songshu.manager.service;

import com.yyc.songshu.manager.pojo.AppContent;

public interface AppContentService {
    String getUse();

    String getPrivacy();

    String getTort();

    String getAll();

    String updateContent(AppContent appContent);

    String delContent(int aId);
}
