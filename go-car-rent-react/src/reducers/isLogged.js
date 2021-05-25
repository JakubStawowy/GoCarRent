const init = {
    logged: !(localStorage.getItem('token') === null)
}

const loginReducer = (state = init, action) => {
    switch (action.type) {
        case 'LOGIN': {
            if (action.payload !== undefined) {
                state.logged = true;
                console.log(action.payload);
                localStorage.setItem('userId', action.payload.data.userId);
                localStorage.setItem('token', action.payload.data.token);
                localStorage.setItem('role', action.payload.data.role);
            }
            return state;
        }
        case 'LOGOUT': {
            state.logged = false;
            localStorage.clear();
            return state;
        }
        default:
            return state;
    }
}

export default loginReducer;