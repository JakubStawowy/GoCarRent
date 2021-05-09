const init = {
    logged: !(localStorage.getItem('token') === null)
}

const loginReducer = (state = init, action) => {
    switch (action.type) {
        case 'LOGIN': {
            if (action.payload !== undefined) {
                state.logged = true;
                localStorage.setItem('userId', action.payload.data.first);
                localStorage.setItem('token', action.payload.data.second);
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