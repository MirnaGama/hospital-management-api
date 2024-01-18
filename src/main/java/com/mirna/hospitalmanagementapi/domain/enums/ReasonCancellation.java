package com.mirna.hospitalmanagementapi.domain.enums;

/**
*  Reasons why the consultation was canceled
*  @see #PATIENT_GAVE_UP
*  @see #DOCTOR_CANCELED
*  @see #OTHER
*/
public enum ReasonCancellation {

	/**
	 * Patient gave up to attend the consultation date
	 */
	PATIENT_GAVE_UP,
	
	/**
	 * Doctor canceled the consultation date and will reschedule
	 */
	DOCTOR_CANCELED,
	
	/**
	 * Any other reason
	 */
	OTHER;
}
