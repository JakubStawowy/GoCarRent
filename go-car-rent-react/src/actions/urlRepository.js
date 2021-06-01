const BASE_API_URL = "http://localhost:8080/api";

export const BASE_RENT_REGISTER_URL = BASE_API_URL + "/rents/register?tenantId=:tenantId&announcementId=:announcementId";
export const BASE_TENANT_RENTS_URL = BASE_API_URL + "/rents/tenant/:tenantId";
export const BASE_FEEDBACK_URL = BASE_API_URL + "/feedback/user/:userId";
export const BASE_USER_DETAILS_URL = BASE_API_URL + "/users/:userId";
export const BASE_LOGIN_URL = BASE_API_URL + "/login?email=:email&password=:password";
export const BASE_LOGOUT_URL = BASE_API_URL + "/logout?id=:userId";
export const BASE_REGISTER_URL = BASE_API_URL + "/register";
export const BASE_EDIT_USER_URL = BASE_API_URL + "/users/:userId/edit";
export const BASE_ANNOUNCEMENTS_URL = BASE_API_URL + "/announcements";
export const BASE_ADD_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/add";
export const BASE_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/:announcementId";
export const BASE_USER_ANNOUNCEMENTS_URL = BASE_ANNOUNCEMENTS_URL + "/user/:userId";
export const BASE_BLOCK_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/:announcementId/block";
export const BASE_DELETE_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/:announcementId/remove?userId=:userId&password=:password";
export const BASE_EDIT_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/:announcementId/edit";
export const BASE_ANNOUNCEMENTS_FILTER_URL = BASE_ANNOUNCEMENTS_URL + "/filter?criteria=:criteria";
export const BASE_UNLOCK_ANNOUNCEMENT_URL = BASE_ANNOUNCEMENTS_URL + "/:announcementId/unlock"
export const BASE_GET_USER_MESSAGES_URL = BASE_API_URL + "/messages/load?userId=:userId";
export const BASE_SEND_MESSAGE_URL = BASE_API_URL + "/rents/send/:number?tenantId=:tenantId&announcementId=:announcementId";
export const BASE_SEND_RENT_RETURN_MESSAGE_URL = BASE_API_URL + "/rents/send/:number?tenantId=:tenantId&rentId=:rentId";
export const BASE_GET_ALL_USER_MESSAGES_URL = BASE_API_URL + "/messages/user?userId=:userId";
export const BASE_ARCHIVE_MESSAGE_URL = BASE_API_URL + "/messages/:messageId/archive";
export const BASE_DELETE_RENT_URL = BASE_API_URL + "/rents/:rentId/delete";