package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.getkeepsafe.relinker.ReLinker;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes2.dex */
public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private String[] sourceDirectories(final Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo.splitSourceDirs != null && applicationInfo.splitSourceDirs.length != 0) {
            String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
            strArr[0] = applicationInfo.sourceDir;
            System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
            return strArr;
        }
        return new String[]{applicationInfo.sourceDir};
    }

    private static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(final Context context, final String[] abis, final String mappedLibraryName, final ReLinkerInstance instance) {
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        int i = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i >= length) {
                return null;
            }
            String str = sourceDirectories[i];
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str), 1);
                    break;
                } catch (IOException unused) {
                    i2 = i3;
                }
            }
            if (zipFile != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    if (i4 < 5) {
                        for (String str2 : abis) {
                            String str3 = "lib" + File.separatorChar + str2 + File.separatorChar + mappedLibraryName;
                            instance.log("Looking for %s in APK %s...", str3, str);
                            ZipEntry entry = zipFile.getEntry(str3);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                        }
                        i4 = i5;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i++;
        }
    }

    private String[] getSupportedABIs(Context context, String mappedLibraryName) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + mappedLibraryName);
        HashSet hashSet = new HashSet();
        for (String str : sourceDirectories(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(str), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(entries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0060, code lost:
    
        r1.zipFile.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return;
     */
    @Override // com.getkeepsafe.relinker.ReLinker.LibraryInstaller
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void installLibrary(android.content.Context r10, java.lang.String[] r11, java.lang.String r12, java.io.File r13, com.getkeepsafe.relinker.ReLinkerInstance r14) {
        /*
            r9 = this;
            r0 = 0
            com.getkeepsafe.relinker.ApkLibraryInstaller$ZipFileInZipEntry r1 = r9.findAPKWithLibrary(r10, r11, r12, r14)     // Catch: java.lang.Throwable -> La9
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L91
            r10 = r2
        La:
            int r11 = r10 + 1
            r4 = 5
            if (r10 >= r4) goto L84
            java.lang.String r10 = "Found %s! Extracting..."
            java.lang.Object[] r4 = new java.lang.Object[]{r12}     // Catch: java.lang.Throwable -> L96
            r14.log(r10, r4)     // Catch: java.lang.Throwable -> L96
            boolean r10 = r13.exists()     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L96
            if (r10 != 0) goto L26
            boolean r10 = r13.createNewFile()     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L96
            if (r10 != 0) goto L26
            goto L82
        L26:
            java.util.zip.ZipFile r10 = r1.zipFile     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L79 java.io.FileNotFoundException -> L7c
            java.util.zip.ZipEntry r4 = r1.zipEntry     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L79 java.io.FileNotFoundException -> L7c
            java.io.InputStream r10 = r10.getInputStream(r4)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L79 java.io.FileNotFoundException -> L7c
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6c java.io.FileNotFoundException -> L6e
            r4.<init>(r13)     // Catch: java.lang.Throwable -> L68 java.io.IOException -> L6c java.io.FileNotFoundException -> L6e
            long r5 = r9.copy(r10, r4)     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> L7e
            java.io.FileDescriptor r7 = r4.getFD()     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> L7e
            r7.sync()     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> L7e
            long r7 = r13.length()     // Catch: java.lang.Throwable -> L66 java.lang.Throwable -> L7e
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 == 0) goto L4b
            goto L7e
        L47:
            r9.closeSilently(r4)     // Catch: java.lang.Throwable -> L96
            goto L82
        L4b:
            r9.closeSilently(r10)     // Catch: java.lang.Throwable -> L96
            r9.closeSilently(r4)     // Catch: java.lang.Throwable -> L96
            r13.setReadable(r3, r2)     // Catch: java.lang.Throwable -> L96
            r13.setExecutable(r3, r2)     // Catch: java.lang.Throwable -> L96
            r13.setWritable(r3)     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L90
            java.util.zip.ZipFile r10 = r1.zipFile     // Catch: java.io.IOException -> L90
            if (r10 == 0) goto L90
        L60:
            java.util.zip.ZipFile r10 = r1.zipFile     // Catch: java.io.IOException -> L90
            r10.close()     // Catch: java.io.IOException -> L90
            goto L90
        L66:
            r11 = move-exception
            goto L6a
        L68:
            r11 = move-exception
            r4 = r0
        L6a:
            r0 = r10
            goto L72
        L6c:
            r4 = r0
            goto L7e
        L6e:
            r4 = r0
            goto L7e
        L70:
            r11 = move-exception
            r4 = r0
        L72:
            r9.closeSilently(r0)     // Catch: java.lang.Throwable -> L96
            r9.closeSilently(r4)     // Catch: java.lang.Throwable -> L96
            throw r11     // Catch: java.lang.Throwable -> L96
        L79:
            r10 = r0
            r4 = r10
            goto L7e
        L7c:
            r10 = r0
            r4 = r10
        L7e:
            r9.closeSilently(r10)     // Catch: java.lang.Throwable -> L96
            goto L47
        L82:
            r10 = r11
            goto La
        L84:
            java.lang.String r10 = "FATAL! Couldn't extract the library from the APK!"
            r14.log(r10)     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L90
            java.util.zip.ZipFile r10 = r1.zipFile     // Catch: java.io.IOException -> L90
            if (r10 == 0) goto L90
            goto L60
        L90:
            return
        L91:
            java.lang.String[] r10 = r9.getSupportedABIs(r10, r12)     // Catch: java.lang.Throwable -> L96 java.lang.Exception -> L99
            goto La3
        L96:
            r10 = move-exception
            r0 = r1
            goto Laa
        L99:
            r10 = move-exception
            java.lang.String[] r13 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L96
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L96
            r13[r2] = r10     // Catch: java.lang.Throwable -> L96
            r10 = r13
        La3:
            com.getkeepsafe.relinker.MissingLibraryException r13 = new com.getkeepsafe.relinker.MissingLibraryException     // Catch: java.lang.Throwable -> L96
            r13.<init>(r12, r11, r10)     // Catch: java.lang.Throwable -> L96
            throw r13     // Catch: java.lang.Throwable -> L96
        La9:
            r10 = move-exception
        Laa:
            if (r0 == 0) goto Lb5
            java.util.zip.ZipFile r11 = r0.zipFile     // Catch: java.io.IOException -> Lb5
            if (r11 == 0) goto Lb5
            java.util.zip.ZipFile r11 = r0.zipFile     // Catch: java.io.IOException -> Lb5
            r11.close()     // Catch: java.io.IOException -> Lb5
        Lb5:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.ApkLibraryInstaller.installLibrary(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.getkeepsafe.relinker.ReLinkerInstance):void");
    }

    private long copy(InputStream in, OutputStream out) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = in.read(bArr);
            if (read != -1) {
                out.write(bArr, 0, read);
                j += read;
            } else {
                out.flush();
                return j;
            }
        }
    }

    private void closeSilently(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
