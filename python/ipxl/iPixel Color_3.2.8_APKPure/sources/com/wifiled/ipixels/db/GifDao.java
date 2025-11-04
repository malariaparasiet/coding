package com.wifiled.ipixels.db;

import com.wifiled.ipixels.model.Gif;
import java.util.List;
import kotlin.Metadata;

/* compiled from: GifDao.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H'J\b\u0010\b\u001a\u00020\u0006H'J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0004H'¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/db/GifDao;", "", "getAll", "", "Lcom/wifiled/ipixels/model/Gif;", "delete", "", "gif", "deleteAll", "update", "add", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface GifDao {
    long add(Gif gif);

    int delete(Gif gif);

    int deleteAll();

    List<Gif> getAll();

    int update(Gif gif);
}
