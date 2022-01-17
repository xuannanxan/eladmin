package com.eladmin.config;


import com.base.core.annotation.FieldDescribe;
import com.base.core.json.JsonPropertyObject;
import com.base.core.tools.NumberTools;
import com.base.core.tools.StringTools;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends JsonPropertyObject {

    private static final long serialVersionUID = 5190363566263723493L;

    public static final Boolean DEFAULT_CAPTCHALOGIN = true;
    public static final Boolean DEFAULT_CODELOGIN = true;
    public static final Boolean DEFAULT_BINDLOGIN = true;
    public static final Boolean DEFAULT_FACELOGIN = true;
    public static final Boolean DEFAULT_SUPERPERMISSION = true;
    public static final Boolean DEFAULT_PERSONUNITORDERBYASC = true;

    public static final String REGISTER_TYPE_DISABLE = "disable";
    public static final String REGISTER_TYPE_CAPTCHA = "captcha";
    public static final String REGISTER_TYPE_CODE = "code";

    public static final String ICON_MANAGER = "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";
    public static final String ICON_MALE = "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";
    public static final String ICON_FEMALE = "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";
    public static final String ICON_UNKOWN = "iVBORw0KGgoAAAANSUhEUgAAAJAAAACQCAYAAADnRuK4AAAAAXNSR0IArs4c6QAAFlZJREFUeAHtXXuMFdUZH5aF5bHLYxFcXgXchV0kuzxNqqVaMca2VBNNFFNTommjVm0Tq8YYTayvNDGFmiY+mqKNYLRR/6hWErEWGjRaElgEsuCyGNgHCKwswrIisEB/v+md69y78zgzc+bMOcueZO6ZO3Me3/fNb77zne88ZpB1gYWWlpaynp6emnPnztWdP3++FuxX4xiD84pBgwZVMMZ/93kvrnfjerc7RppuHJ241oyymktKSprnz5/fhv/ncf2CCYP6M6fbt28fe+bMmR/h4S/Gg52NuBbxdMQlKfF9EuW3oPxmlL8DxwYcmxYtWnQmpfoyL7ZfAaipqan89OnTV549e3YJJHs1Hua8FMEi9PBAQw8Sfox4A4718+bNa0R8ViizAYmMBxBAU3ny5MllkPXPcXwfR6nmcj8GAL0PYK9ZuHDhOpz3ak5vIHlGAgigGQrQ/BTCXw7uluJhDA3kUtOboP8wSHsD9K9GM9eoKZmBZBkFoM2bN9dD6HdD4NQ44wI5M+9mE3hbPWzYsFVz5szpMoV8IwC0devWy2DXPAoB3wDwGEFzAgCwt/fi0KFDV9bX1x9KUI6SrFo/jMbGxivRRX4MkrhWiTT0qoQ9upcBpGcBpHa9SPuOGi0BtGXLliXQNE+AzMXfkXrBntEFsBrHk7CT2nSTglYA+uyzzyajqVoJ8Nyim6A0oOcb0PAUjhU6+ZW0ABAAUwo757dorn4PAdETPBB8JIBmbRfkdS9ARCdl5iFzAKFnxWbqBRz1mUvDLAJeHz58+APosR3MkuzMALR3795hR44c+RPeqLvwRmVGR5bCl1D3cYzB3bdgwYI1EsqKVUQmD27btm21vb29bwI4DbGoHshUIAG8hK9MmDDhvqlTp54suKHgj3IAoYd1G/h6CeApV8DfhVQFHZE3Y3hkl0qmlQGovb19+KFDh/4M5n6lksELqS4AqCdnYL+qim8lAIKh/D0w9B6OAUNZzZNdBU30awAq9YHa1AEE384c+HbW4c2YrEZ2A7XkJLAW8S3o7tN/lFpIa2KVTTB8O1cAPB8NgCe15xdU8FJooA853SUoUdJ7qWkgGMs/A3DeBIHDkxI5kD++BACinaWlpdfNnTu3I34p/jlTARDAczvA81dUq/vkLn/J9K877QDSdWn00KQDKAeev/Uv+ZvPDQDEyWs/AIj2yORGqg2Ua7aoeQaCZhJAizABxwewiapkkiZNA+UM5g9B3IDNI/MJSS4LmmgbgHQVemfHZBQtRQOxq46RdPp5BsAj46mkWAbAMxcgeofr42RUk9jIpZMw5+cZK4OgrMoYPHiwhfnIFmYAWhigtA8I2wJv9oHlQtapU6csXjM9UAMdP36ck/lvBpgSLTFK1ITlhic2QaDGeZgJloqKCmvUqFFWeXm5ha5uKC4IHoLoxIkTVnd3t4WHYGFQODSfrgkAnj/CqH4oCX2JAATtswqV/zIJASrzUrNUVlZa48ePt0aMGJG4agLq66+/tr766isbTIkLVFwAAAQWzl8Pe4he61ghNoDQ47oNlb8Wq1bFmQicqqoqC1MeLDZVaQRqJsxvssGE5dRpVJFWmUfKysrmx524HwtAufk8mwEg7adkEDQTJ04UaqJkPCHaTF9++aV1+PBhY+wlaKJPMCntKsSR2+PIAOJMwq6urk0Aj9aTwWjjTJ8+3Ro5cqQMXEQu49tvv7Xa2tpsWyly5gwyADzPwh56OGrVkQEEu+dFVHJ31IpUpqfWmTx5st2TUlmvV1142WwgUTPpHGgPgb6fAETrotAZCUAAD7dJ2QjtEylfFIKSpKWtQ60zdqxeHgWs47f27Nlj0RWgc8CzbcWzvTTKFBBhRyIKZj/3BV3BM2TIEKuurk478BAwWD1h05ZVcyoKWjzbaQDRo6LpmU5Yk2CZ8e/gbV4RpXBVadGLsGbOnGkx1jlAfta+ffuso0ePaksmAHQaPrEGTP/gJlmhQUgDccVobtFfaIGqE9BzPGvWLO3BQ7mwiZ0xY4Y1evRo1WISrg9aaCico8+LZhACEAzAlSiwQrRQVeno06mpqbGHH1TVmbQevOHWJZdcIsWRmZQWv/wA0TVocW71u+++HgogOAy50YGWa9X5NtO+MC04moixrgEtzkr4+0J9IKEcADxP6Mgku+o6NwVhMqOfatq0aWHJsrw/ES3PXWEEBAKI+/OggMVhhai+zx4X/TymB47L8dA1QAs9GDbtIxBAKOAxHZnTxUkoQzbkhXaRpmEiZh3cEUSbL4C4rRwyarczGLvqOr+1QcL2usde5EUXXeR1S4trMGEexuE718UXQGj/IjmUVHFL20fjNzaWGHR+IQCe6Zx54ceYJ4AwZMHdUG/wy5TldZ2FHVcu9FDTrtM4PAIgeWLFUzUBPNxKV7uGmbMHRWYOJnkQnG1ITzG+p2FxXg9nHNLfRHcBe33jxo2TPqeIGnXMmDFWZ2dnEtLTzFsLk+ZqVPDv4kr6ACi3ifey4oQ6/OfU07QCZxYeOHDA4sBncUBnwgYTp7Du37/fnpjGOUYy/TgEp8YAsiADbureB0B91BJ3gEdCLTfxTsNpSA3zxRdf2IcXeLzAdPDgQWvnzp2eYCtOL/o/Dd5E6xZJBy15k5djsQ+AkJBI0zLIFjInfe3atcue1xyVYU5hbW5utpu6qHm90rM3ltZ0W6/6ol6DSVOOl+3G4nwFAMrt5LC0OJEO/9lcyBxtJ3gIgCRzdDhJDI62RGW4ZUvvtM4BIOqjXAoABBW+DIm0/HCJbOMZU3OlLMkhiFpbW6U8d90BhNaJg6yT3MwWAAg3+MkkLYNMg5XG6jffyNt3icY1e21Jg+ZdeS4SIF4KRunzAPr88885XYPf29IysKsrI0AI9qoJGWW5yzh2LPlSc51tIIdXyK9gdCIPIDRfP0SiPt16J2PWsSwNxO562Lot1kWHJcepROdX03+UNJgAIPC4GI7mvNczDxi05UuSCiDN/PTFyAhc/BcU2BviJDV3j49NHpfoBAURF0BQft4zAUDQQOWgk+Okn5DmvAbCudYACtMaZEYkBNkqbCarq6sLwMMyuRRaZg/Qj05ZWtavfFnXAaI8VmwAsfsO4c2VVUEa5dDhR/slaQjSZFz+7LdmPmxFhYyHL4O/pPIRyQ8ZFgIIPpGrQLwNJpECskrDLnPS4AcENlkcnvALQcBjHt274H58xbx+OVcoM68DGhrQ2ockTj+HOXxPos+ALJsnTnT36+kRPEFNH8uWMU5nigYCu/xQziLy7RjRdfyje+DwgV8TI0o7Nc3s2bPtgUunPE7oCnJU0lEYZoNxND1pMAhAfNlmg9+PbQCB8NqkzKvIz+EHGYE9LZE51QQNlySHOR05zcTda4tLo4wmOm7dUfM5mCnhpGmgaXrUArJIT42hMoiAh83epEkF3v3YJJoEIPBtK50SvF0zgSbHForNvIqMsjSQCK2cVBameVgOe25+hrlIPe407GkaFGwAlQL1RjRfFKxKAIU5HKl52AxefPHF0p65SQCC0plBj3Sp05ZJk0KKBVHFU8hBBq+M6tnr4iaafoHgobeato/MEGaoy6xLQlm0n6vZdNVIKExZETKGDMKIZZc9yO/DlSGywUOaZLgpwniTeR8vUg0BlLz/KZOqkLJUNGNBtg+1j8xmy82uYRqIXfmxBJB2u264hVp8rgJAQVqOe0unMW+H4AnSesVy0OE/6C0vgQ1kFICCHq4soQZpIAIojaDaRSGDB2igihL+yChMVRkqduQIakpkddmL5cWpHGl3DorrTPqfyocaKL3FVkkpLMpP2yMt+8OpCvIInCstw+Ps1OWOWS57drSxTAlGaSC+obI8vkEPKEj7cMpGmlqC2k3njRY85FZBP5ARTRibLhlzbjyE0OeS30NMEzwOEVw6rfMKVYfOXFxOZxD95/agatFNrf4mHYUXZYYDrVnuHEY+2YyxKdU9gMZeGtH+LleNOEij66wRe3lSCB5TeCV2aEQbAaC8hC+AE4LIhADsnCglikxQl0HGbRrC5jovfnWHjktOV+V0VxUuBPKimte48jNKA6l0tBE8nAvEMTEO4DLmfxmLB8MeFgeLTfFI2wDiTxhTOtxX4YF2+KTm8Qp+173Sxr2mks+4NDr5aP5wLMwIAHF4QVVT6zfe5nfdEaiMOGzyvow6ZJVhayAUpu2+am5GqdaD5ui40yY991ui43c9aX3u/NyowZSAF/owu/FCX2XRgSl+3FZF8Fsf5nddFk1svlS9JDJohmN1dwnebGMAxHnKKgTM3hbHpTi0wCEUxvyfZi+MzXPY+nsZD11WGVA8PQ0NDftLMTzQbIrVT+bZG+JHVmSswwoSJsGSJmDcdbOnx++Iydjhw11umucA/G6A6HzJ/Pnz21BR361J06w9QdkEOzfF3L17t9RNLhOQFDsrtQ6/7rxjx45Y+zTGrlhCRsf0oQ10HkeLhDKVFsGmDJtiGQ2ijo4Oq7293fY1KRWenMps04fdeHaPjbGD3LxTG6nwzbjrlHVOxyi1j6khr4FyDOwwlREVRnUasjGpu+7FP2zn7bxuayDEG7wSmXCNrn/TlsNQrkHzrnWXO7RPJz7Ku5N0OgDaRJ50J9yPPpO8tw4PJtLs0I54A21n/rcBhA/Nn8H5x7xgYjCp+0v5UmuaNOblgYn1zjVHA3G6aP6ic9OU2DQ7yDR6i3HgxkoeQFBJxgKIb7PK6R7FAo36n1sNGxw64DvMu33yAJo3b14jmEq+W3ZGkunq6sqo5mjV0vVgMoCgaAo6XHkA4cZZHO9HE4c+qTnQSs+u7oFAN2noyEOea93X8gDiRTyANe6bJp2zK6+7FiLA+a0xg8Nx7Ezyrpv+AgAtXLhwHbSQse5ReqV1frsJcJNsNTdQcudvYZfbgnHTAgABPFwj9oZHRiMu8eHo+oZzxJ1jXyYH9L76tFAFACJzULOrTWaSANLRy8tBU/p/TA1QLvvQ+9pYTH8fAMGpyN5YU3FCU/7TzuDcGsa6BI57he25qAutfnRAnq8BRH2E2gdALAAJjdZC9AvpZFCb3nQRE5i+2qf54nVPAGHy+CrcM2K1BpnwCpz+qkNgc2r4sAUVynvwE+72kqcngObMmdOFTC96ZTDlmi7jY6YPW+Se9zN+z90TQEyMXSpWIirosvkVouN19np06NKbskzZ7xlCkayHe+e/fvd9AVRfX38ImV/2y2jCdYIo66ADDUlkAOP56aD8vgBiJmihZxFxqoeRAX6LzOnGS5g5DXEJAO2foldeMPZVXFaghKGF2pFhdXEmU/5zTVfWwWQAQfv42j6OXAMBlEv0JGLjZivqAB7KTxc6cs9SOALwN0L7FAycemUOBRAKaUPGp7wy63xNxX6GIvxzyzwDQy+Af48I3aEAyhWyAojcJVKgLmnS2o43Kn8mAgjP+jn4fYRGI4QABC10Bu3hvVGFl2V6VZtyhvGoCx1hdLrud8CR/ITrf+CpEIBYQs4afz2wNI1u6vLg2JSq2BZGlujRc70fjuQTouUJA4gFoll4AJH2G9jQcE3rmxaignWnU7VJg7vOOOdoutYtWLDg7Sh5IwEIyDwIhN4XpYIs0nKjcB18QA7v3DzcgNAFM+XOqHRGAhALB0LXAKmvRK1IZfrx48errC60Lhr0OmlEL4LxTG/P9bi9bvteiwwgloR5sdRCQla6b80p3eDXBMvKylIqPX6xU6ZMiZ85/ZwrMN71zzjVxAIQ58UCsTfj6IlTaVp5aKyKfA8+rfqDyqVRT3BrGLis/ZG4dMUCECsDYnfp1LUHmO2dy3SyfYofCrVQeXl58eXM/kNmRyGvZXTTxCUiNoBYISp+FREnn2Ua2OuaNWuWpUvX3U8YBHl1dbUWdIKWc6BzOWzaVj96Ra4nHiqGFirdsmXLP1DZUpEKZaehn2XmzJlaPBRR3ji53tkJXzSP7HQA0N1oRf6StNxEGoiVgxAuNbgF8adJiYmanxqnrq7OKPCQR4KeGjNDm+hxGeAhL4k1EAthaGpqqsRO7h9BI136/yvp/QKsVlVVlf0BFJ6bHLhOntv7qpq5CHm9APBIG5aSKv1t27ZNgSA+wQOdmtZDHTVqlIVeoFHDA2Gy4KzFAwcOqNgz8W2AZxlARPtHSpAKIFIEe2g2ov9AE0nts3Kzb3bRdXfIJXkq/BYHgZTSipJ/YUjletiLp5LQWJxXOoBYAUBUAwB9gNMZxRVG/U+Nw+aqPwOnWCZcBsQVtgQS5Fh8O87/twCeX8gGDwlJBUAsGDZRFd6o9yGAufwfJdCXw/EjDknoMq8nCv2y0nLHEW4FzK1r4k7Op82DrvpvZDZbbv5SAxAr2bx582gQ/g5AdJW7Ur9z9qo4EFpZWWnsVFA/3pJc5/IkaiMCKeJ6t8fhq3sySd1heVMFECtvaWkpw9rwNwCiG4OIYRNFJ5upc4iDeJN5j702fuohKOS0zT2yuuqBdQXdlHUP4Bnc2Nj4B5T3IM59QcuvFfOT26bMn5ElH5FyIDfbLuIeSDz3CwAP13QvB3je80sj87rvw5RZiVMWmjR6q1/FEThBhvYPx410mRjv0J9VzPX1ra2tItvWbOLYVtLhiSh8KgUQCcOXaabCOPw73qIrgggleCZNmmTbRHirgpL223sc8qDG6ezsDNQ6OQGsQPxIkoHROILM5MkAPKVo0rho7SGcB9LAKRrURhdSs0ajmb0vduUFel/cCON2NFmx5vPEAY07T+DDcydM4xz+oh+j3JcAomlh5dPI5icn+7M/CHKw9zWiM1Hk+x8AzjrkuRNapy1MfmndzxRAZAp20QgI4lGc0sAOXYXHrj4di/xiYX9p2qhl2ExR6wiOiXXA1rkftk6kCfBpgChzADlMYRytFm3+8wDRNc61oJjTVjmaTZ+RqcY2hy4IHPp3BLei6cVL8xzXbUVZehMkx6T3tAGQwwhso1sBohU4JjnXgmJqIdpHBBJjerF1DtQwdApyz8Qom4GCz41cbiy6YlSVDLQDEBmHNhoJtX4X3soH8XeiqDDohGTTxvEzHrpoJm4/zI026QSM+qE5AOdTvEzPwM5ZKyoHlem0BJAjAHqxsUXcHRDgwzimO9dFY47gE0ich0zbSRWgaADze2Dc3o6AibO5OICzHjw/DeBsEOU3i3RaA8gRCATJabO34T9XD9Q616PGtJsIJB4858YHjOMACzTZBi/BwoMj6GySeCTZDxrAoQf5GXTLfbeVi8p3mumNAJAjADy0kq1bt16Npm05BH0T/ktZ4kC7iWBiE8hz98G6aeA6B3tMBIhIN9uhOywGL/vAy2sA8hq/3VDDysjqvlEAcguJdhIe5I0QPMF0DcHlvm/AOfcYeAtgXcMd4MGD/wCXxswYCyC3TNFzY4+NvbdrES9GLEUzueuQdN4BoNCmWQsXxLvFHy6RVIfSYvoFgNwSg2NyCJqiywCiJWh2luDe5TiGudOoOgdYOlEXAbMemma9+0t/qmhIu55+B6Bige3du3cYfC6L8DBnA1S1iGmE1+J8BuLS4vRx/qPMHpS3G3Ez8jczBmC289PYODeyaRKVQ78HkJ8gqKlwrxoPuAbHWGircsQVAEIFY9zjUY7//JJjN+Juxq5zbsJ0GNquuaGhYT+u92uggFfP8D85spfwanUgIQAAAABJRU5ErkJggg==";

    public static final String REGULAREXPRESSION_SCRIPT = "^\\((.+?)\\)$";

    public static final String DEFAULT_PASSWORD = "(var v \\u003d person.getMobile();\\u000a return v.substring(v.length - 6))";
    public static final Integer DEFAULT_PASSWORDPERIOD = 0;
    public static final Integer DEFAULT_FAILUREINTERVAL = 10;
    public static final Integer DEFAULT_FAILURECOUNT = 5;
    public static final Integer DEFAULT_TOKENEXPIREDMINUTES = 60 * 24 * 15;
    public static final Boolean DEFAULT_TOKENCOOKIEHTTPONLY = false;

    public static final String DEFAULT_PASSWORDREGEX = "((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,}$";
    public static final String DEFAULT_PASSWORDREGEXHINT = "6位以上,包含数字和字母.";
    public static final String DEFAULT_LANGUAGE = "zh-CN";
    public static final String DEFAULT_CAPTCHAFONT = "";
    public static final String DEFAULT_TOKENNAME = "x-token";

    public static final Boolean DEFAULT_ENABLESAFELOGOUT = false;

    public Person() {
        this.captchaLogin = DEFAULT_CAPTCHALOGIN;
        this.codeLogin = DEFAULT_CODELOGIN;
        this.bindLogin = DEFAULT_BINDLOGIN;
        this.faceLogin = DEFAULT_FACELOGIN;
        this.password = DEFAULT_PASSWORD;
        this.passwordPeriod = DEFAULT_PASSWORDPERIOD;
        this.register = REGISTER_TYPE_DISABLE;
        this.superPermission = DEFAULT_SUPERPERMISSION;
        this.passwordRegex = DEFAULT_PASSWORDREGEX;
        this.passwordRegexHint = DEFAULT_PASSWORDREGEXHINT;
        this.personUnitOrderByAsc = DEFAULT_PERSONUNITORDERBYASC;
        this.tokenCookieHttpOnly = DEFAULT_TOKENCOOKIEHTTPONLY;
        this.language = DEFAULT_LANGUAGE;
        this.captchaFont = DEFAULT_CAPTCHAFONT;
        this.tokenName = DEFAULT_TOKENNAME;
        this.enableSafeLogout = DEFAULT_ENABLESAFELOGOUT;
    }

    public static Person defaultInstance() {
        return new Person();
    }

    public static final Integer MAX_PASSWORDPERIOD = 365 * 10;

    @FieldDescribe("是否启用验证码登录,默认值:true")
    private Boolean captchaLogin;

    @FieldDescribe("是否启用验证码登录,默认值:true")
    private Boolean codeLogin;

    @FieldDescribe("是否启用扫描二维码登录,默认值:false")
    private Boolean bindLogin;

    @FieldDescribe("是否启用刷脸登录,默认值:false")
    private Boolean faceLogin;

    @FieldDescribe("注册初始密码,使用()调用脚本生成初始密码,默认为:" + DEFAULT_PASSWORD)
    private String password;

    @FieldDescribe("密码过期时间(天),0表示不过期,默认值:0.")
    private Integer passwordPeriod;

    @FieldDescribe("密码校验正则表达式,默认6位以上,包含数字和字母.")
    private String passwordRegex;

    @FieldDescribe("密码校验不通过的提示信息.")
    private String passwordRegexHint;

    @FieldDescribe("是否允许用户自注册,disable:不允许,captcha通过验证码注册,code:通过短信注册,默认值:disable")
    private String register;

    @FieldDescribe("是否启用超级管理员权限,默认值:true")
    private Boolean superPermission;

    @FieldDescribe("手机号码校验正则表达式,()表示脚本内容,默认值:(^(\\+)?0{0,2}852\\d{8}$)|(^(\\+)?0{0,2}853\\d{8}$)|(^(\\+)?0{0,2}886\\d{9}$)|(^1(3|4|5|7|8|9)\\d{9}$)")
    private String mobileRegex;

    @FieldDescribe("定制登录页面设置.")
    private LoginPage loginPage;

    @FieldDescribe("登录限制时间(分钟)")
    private Integer failureInterval;

    @FieldDescribe("尝试登录次数")
    private Integer failureCount;

    @FieldDescribe("token时长,分钟")
    private Integer tokenExpiredMinutes;

    @FieldDescribe("保存token的cookie是否启用httpOnly")
    private Boolean tokenCookieHttpOnly;

    @FieldDescribe("使用识别用户的token名称,可自定义,默认为:" + DEFAULT_TOKENNAME + ".")
    private String tokenName;

    @FieldDescribe("人员组织排序是否为升序，true为升序(默认)，false为降序")
    private Boolean personUnitOrderByAsc;

    @FieldDescribe("平台语言：zh-CN(中文，默认)、en(英语)")
    private String language;

    @FieldDescribe("验证码字体,默认为空,代表使用系统自带字体.")
    private String captchaFont;

    @FieldDescribe("是否启用安全注销.")
    private Boolean enableSafeLogout;

    @FieldDescribe("维护人信息.")
    private Maintainer maintainer;

    public Maintainer getMaintainer() {
        return (null == maintainer) ? Maintainer.defaultInstance() : this.maintainer;
    }

    public Boolean getEnableSafeLogout() {
        return BooleanUtils.isTrue(this.enableSafeLogout);
    }

    public Boolean getTokenCookieHttpOnly() {
        return BooleanUtils.isTrue(this.tokenCookieHttpOnly);
    }

    public Integer getTokenExpiredMinutes() {
        return (this.tokenExpiredMinutes == null || this.tokenExpiredMinutes < 0) ? DEFAULT_TOKENEXPIREDMINUTES
                : this.tokenExpiredMinutes;
    }

    public Integer getPasswordPeriod() {
        if ((null == this.passwordPeriod) || (this.passwordPeriod < 0)) {
            return 0;
        } else if (this.passwordPeriod > MAX_PASSWORDPERIOD) {
            return MAX_PASSWORDPERIOD;
        } else {
            return this.passwordPeriod;
        }
    }

    public static class LoginPage extends JsonPropertyObject {

        private static final long serialVersionUID = -1960810257119355612L;

        public static LoginPage defaultInstance() {
            return new LoginPage();
        }

        public LoginPage() {
            this.enable = false;
            this.portal = "";
            this.page = "";
        }

        @FieldDescribe("是否启用定制登录页面.")
        private Boolean enable;
        @FieldDescribe("指定登录页面所属的portal,可以用id,name,alias.")
        private String portal;
        @FieldDescribe("指定的登录页面,可以使用name,alias,id")
        private String page;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public String getPortal() {
            return portal;
        }

        public void setPortal(String portal) {
            this.portal = portal;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

    }

    public String getTokenName() {
        return StringUtils.isBlank(this.tokenName) ? DEFAULT_TOKENNAME : this.tokenName;
    }

    public Integer getFailureInterval() {
        return (NumberTools.nullOrLessThan(this.failureInterval, 0) ? DEFAULT_FAILUREINTERVAL : this.failureInterval);
    }

    public Integer getFailureCount() {
        return (NumberTools.nullOrLessThan(this.failureCount, 0) ? DEFAULT_FAILURECOUNT : this.failureCount);
    }

    public LoginPage getLoginPage() {
        return (null == loginPage) ? LoginPage.defaultInstance() : this.loginPage;
    }

    public String getPassword() {
        return StringUtils.isEmpty(this.password) ? DEFAULT_PASSWORD : this.password;
    }

    public Boolean getSuperPermission() {
        return BooleanUtils.isTrue(this.superPermission);
    }

    public String getPasswordRegex() {
        return StringUtils.isEmpty(this.passwordRegex) ? DEFAULT_PASSWORDREGEX : this.passwordRegex;
    }

    public String getPasswordRegexHint() {
        return StringUtils.isEmpty(this.passwordRegexHint) ? DEFAULT_PASSWORDREGEXHINT : this.passwordRegexHint;
    }

    public String getRegister() {
        if (StringUtils.equalsIgnoreCase(REGISTER_TYPE_CODE, this.register)) {
            return REGISTER_TYPE_CODE;
        } else if (StringUtils.equalsIgnoreCase(REGISTER_TYPE_CAPTCHA, this.register)) {
            return REGISTER_TYPE_CAPTCHA;
        } else {
            return REGISTER_TYPE_DISABLE;
        }
    }

    public Boolean getCaptchaLogin() {
        return BooleanUtils.isTrue(this.captchaLogin);
    }

    public Boolean getCodeLogin() {
        return BooleanUtils.isTrue(this.codeLogin);
    }

    public Boolean getBindLogin() {
        return BooleanUtils.isTrue(this.bindLogin);
    }

    public Boolean getFaceLogin() {
        return BooleanUtils.isTrue(this.faceLogin);
    }

    public String getMobileRegex() {
        return StringUtils.isEmpty(this.mobileRegex) ? StringTools.MOBILE_REGEX.toString() : this.mobileRegex;
    }

    public String getCaptchaFont() {
        return StringUtils.isBlank(this.captchaFont) ? DEFAULT_CAPTCHAFONT : this.captchaFont;
    }

    /*
     * 判断是否符合手机号码格式
     */
    public boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher m = Pattern.compile(this.getMobileRegex()).matcher(mobile);
        return m.find();
    }


    public void setCodeLogin(Boolean codeLogin) {
        this.codeLogin = codeLogin;
    }

    public void setBindLogin(Boolean bindLogin) {
        this.bindLogin = bindLogin;
    }

    public void setFaceLogin(Boolean faceLogin) {
        this.faceLogin = faceLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordPeriod(Integer passwordPeriod) {
        this.passwordPeriod = passwordPeriod;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public void setSuperPermission(Boolean superPermission) {
        this.superPermission = superPermission;
    }

    public void setMobileRegex(String mobileRegex) {
        this.mobileRegex = mobileRegex;
    }

    public void setLoginPage(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void setCaptchaLogin(Boolean captchaLogin) {
        this.captchaLogin = captchaLogin;
    }

    public void setFailureInterval(Integer failureInterval) {
        this.failureInterval = failureInterval;
    }

    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }

    public void setTokenExpiredMinutes(Integer tokenExpiredMinutes) {
        this.tokenExpiredMinutes = tokenExpiredMinutes;
    }

    public Boolean getPersonUnitOrderByAsc() {
        return BooleanUtils.isTrue(this.personUnitOrderByAsc);
    }

    public void setPersonUnitOrderByAsc(Boolean personUnitOrderByAsc) {
        this.personUnitOrderByAsc = personUnitOrderByAsc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPasswordRegex(String passwordRegex) {
        this.passwordRegex = passwordRegex;
    }

    public void setPasswordRegexHint(String passwordRegexHint) {
        this.passwordRegexHint = passwordRegexHint;
    }

    public void setTokenCookieHttpOnly(Boolean tokenCookieHttpOnly) {
        this.tokenCookieHttpOnly = tokenCookieHttpOnly;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public void setCaptchaFont(String captchaFont) {
        this.captchaFont = captchaFont;
    }

    public void setEnableSafeLogout(Boolean enableSafeLogout) {
        this.enableSafeLogout = enableSafeLogout;
    }

    public static class Maintainer extends JsonPropertyObject {

        private static final long serialVersionUID = 9067834464099067485L;

        public static final Boolean DEFAULT_ENABLE = false;
        public static final String DEFAULT_NAME = "";
        public static final String DEFAULT_UNIT = "";
        public static final String DEFAULT_MOBILE = "";

        public static Maintainer defaultInstance() {
            return new Maintainer();
        }

        public Maintainer() {
            this.enable = DEFAULT_ENABLE;
            this.name = DEFAULT_NAME;
            this.unit = DEFAULT_UNIT;
            this.mobile = DEFAULT_MOBILE;
        }

        @FieldDescribe("是否启用")
        private Boolean enable;
        @FieldDescribe("维护者姓名")
        private String name;
        @FieldDescribe("组织")
        private String unit;
        @FieldDescribe("手机号")
        private String mobile;

        public Boolean getEnable() {
            return BooleanUtils.isTrue(enable);
        }

        public String getName() {
            return StringUtils.isEmpty(this.name) ? DEFAULT_NAME : this.name;
        }

        public String getUnit() {
            return StringUtils.isEmpty(this.unit) ? DEFAULT_UNIT : this.unit;
        }

        public String getMobile() {
            return StringUtils.isEmpty(this.mobile) ? DEFAULT_MOBILE : this.mobile;
        }

    }
}
