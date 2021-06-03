import {makeStyles} from "@material-ui/core";

export const useUserCarsStyles = makeStyles((theme) => ({
    tableHead: {
        background: '#4BBEBAE0',
    },
    container: {
        padding: '1em',
        [theme.breakpoints.down('xs')]: {
            maxHeight: '86vh',
            overflow: 'auto'
        }
    },
    check: {
        color: "green"
    },
    clear: {
        color: "red"
    }
}));