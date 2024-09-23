import request from "./axiosClient";

const END_POINT = "v1/users/";

const userService = {
    getAll() {
        return request.get(END_POINT + "get-all", {
            //   params,
        });
    },
    get(userId: number) {
        return request.get(END_POINT + userId);
    },
    create(payload: any) {
        return request.post(END_POINT + "create", payload);
    },
    update(userId: number, payload: any) {
        return request.put(END_POINT + "edit/" + userId, payload);
    },
    delete(userId: number) {
        return request.delete(END_POINT + userId);
    },
};

export default userService;