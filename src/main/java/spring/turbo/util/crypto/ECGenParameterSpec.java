package spring.turbo.util.crypto;

/**
 * EC Parameter
 *
 * @author 应卓
 * @since 3.3.1
 */
public enum ECGenParameterSpec {

    secp256r1("secp256r1");

    private final String stdName;

    private ECGenParameterSpec(String stdName) {
        this.stdName = stdName;
    }

    public String getStdName() {
        return stdName;
    }

}
