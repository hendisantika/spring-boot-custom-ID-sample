package com.hendisantika.util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-custom-ID-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 01/10/21
 * Time: 19.08
 */
public class DatePrefixedSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String DATE_FORMAT_PARAMETER = "dateFormat";
    public static final String DATE_FORMAT_DEFAULT = "%tY-%tm";

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%05d";

    public static final String DATE_NUMBER_SEPARATOR_PARAMETER = "dateNumberSeparator";
    public static final String DATE_NUMBER_SEPARATOR_DEFAULT = "_";

    private String format;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
        return String.format(format, LocalDate.now(), super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);

        String dateFormat =
                ConfigurationHelper.getString(DATE_FORMAT_PARAMETER, params, DATE_FORMAT_DEFAULT).replace("%", "%1");
        String numberFormat =
                ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT).replace("%",
                        "%2");
        String dateNumberSeparator = ConfigurationHelper.getString(DATE_NUMBER_SEPARATOR_PARAMETER, params,
                DATE_NUMBER_SEPARATOR_DEFAULT);
        this.format = dateFormat + dateNumberSeparator + numberFormat;
    }
}
