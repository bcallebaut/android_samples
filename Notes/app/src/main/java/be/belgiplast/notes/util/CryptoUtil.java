package be.belgiplast.notes.util;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CryptoUtil {
    private X509Certificate cert;

    public CryptoUtil(Application app) {
        PackageManager pkg = app.getPackageManager();
        String packageName = app.getPackageName();
        try{
            PackageInfo info = pkg.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            final Signature[] arrSignatures = info.signatures;
            for (final Signature sig : arrSignatures) {
                final byte[] rawCert = sig.toByteArray();
                InputStream certStream = new ByteArrayInputStream(rawCert);

                try {
                    CertificateFactory certFactory = CertificateFactory.getInstance("X509");
                    X509Certificate x509Cert = (X509Certificate) certFactory.generateCertificate(certStream);
                    cert = x509Cert;
                    PublicKey pub = x509Cert.getPublicKey();
                }
                catch (CertificateException e) {
                    // e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
