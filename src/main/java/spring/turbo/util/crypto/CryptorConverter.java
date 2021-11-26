/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class CryptorConverter implements GenericConverter {

    private static final Set<ConvertiblePair> CONVERTIBLE_TYPES;

    static {
        final Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(PasswordAndSalt.class, AES.class));
        set.add(new ConvertiblePair(PasswordAndSaltProvider.class, AES.class));
        set.add(new ConvertiblePair(StringifiedKeyPair.class, RSA.class));
        set.add(new ConvertiblePair(StringifiedKeyPair.class, DSA.class));
        set.add(new ConvertiblePair(StringifiedKeyPair.class, ECDSA.class));
        set.add(new ConvertiblePair(StringifiedKeyPairProvider.class, RSA.class));
        set.add(new ConvertiblePair(StringifiedKeyPairProvider.class, DSA.class));
        set.add(new ConvertiblePair(StringifiedKeyPairProvider.class, ECDSA.class));
        CONVERTIBLE_TYPES = Collections.unmodifiableSet(set);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_TYPES;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            return doConvert(source, sourceType, targetType);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private Object doConvert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        // AES
        if (targetType.getObjectType() == AES.class && (source instanceof PasswordAndSalt)) {
            return AES.builder()
                    .passwordAndSalt(((PasswordAndSalt) source).getPassword(), ((PasswordAndSalt) source).getSalt())
                    .build();
        }

        // AES
        if (targetType.getObjectType() == AES.class && (source instanceof PasswordAndSaltProvider)) {
            PasswordAndSalt pas = ((PasswordAndSaltProvider) source).getPasswordAndSalt();
            return AES.builder()
                    .passwordAndSalt(pas.getPassword(), pas.getSalt())
                    .build();
        }

        // RSA
        if (targetType.getObjectType() == RSA.class && (source instanceof StringifiedKeyPair)) {
            return RSA.builder()
                    .keyPair(RSAKeys.fromString(
                            ((StringifiedKeyPair) source).getPublicKey(),
                            ((StringifiedKeyPair) source).getPrivateKey()
                    ))
                    .build();
        }

        // RSA
        if (targetType.getObjectType() == RSA.class && (source instanceof StringifiedKeyPairProvider)) {
            StringifiedKeyPair kp = ((StringifiedKeyPairProvider) source).getStringifiedKeyPair();
            return RSA.builder()
                    .keyPair(RSAKeys.fromString(
                            kp.getPublicKey(),
                            kp.getPrivateKey()
                    ))
                    .build();
        }

        // DSA
        if (targetType.getObjectType() == DSA.class && (source instanceof StringifiedKeyPair)) {
            return DSA.builder()
                    .keyPair(DSAKeys.fromString(
                            ((StringifiedKeyPair) source).getPublicKey(),
                            ((StringifiedKeyPair) source).getPrivateKey()
                    ))
                    .build();
        }

        // DSA
        if (targetType.getObjectType() == DSA.class && (source instanceof StringifiedKeyPairProvider)) {
            StringifiedKeyPair kp = ((StringifiedKeyPairProvider) source).getStringifiedKeyPair();
            return DSA.builder()
                    .keyPair(DSAKeys.fromString(
                            kp.getPublicKey(),
                            kp.getPrivateKey()
                    ))
                    .build();
        }

        // ECDSA
        if (targetType.getObjectType() == ECDSA.class && (source instanceof StringifiedKeyPair)) {
            return ECDSA.builder()
                    .keyPair(ECDSAKeys.fromString(
                            ((StringifiedKeyPair) source).getPublicKey(),
                            ((StringifiedKeyPair) source).getPrivateKey()
                    ))
                    .build();
        }

        // ECDSA
        if (targetType.getObjectType() == ECDSA.class && (source instanceof StringifiedKeyPairProvider)) {
            StringifiedKeyPair kp = ((StringifiedKeyPairProvider) source).getStringifiedKeyPair();
            return ECDSA.builder()
                    .keyPair(ECDSAKeys.fromString(
                            kp.getPublicKey(),
                            kp.getPrivateKey()
                    ))
                    .build();
        }

        return null;
    }

}
