package com.ipn.escom.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

	private static final Logger LOGGER = Logger.getLogger(PersistenceUtil.class.getName());
	private static final String DEV_PERSISTENCE_UNIT = "dev";
	private static EntityManagerFactory devEntityManagerFactory;

	private PersistenceUtil() {
	}

	public static EntityManager getDevEntityManager() {
		if (devEntityManagerFactory == null) {
			LOGGER.log(Level.INFO, "Creating factory");
			devEntityManagerFactory = Persistence.createEntityManagerFactory(DEV_PERSISTENCE_UNIT);
			LOGGER.log(Level.INFO, "FACTORY CREATED");
		}
		return devEntityManagerFactory.createEntityManager();
	}

}
