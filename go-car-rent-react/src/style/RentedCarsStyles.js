import {makeStyles} from "@material-ui/core";

export const useRentedCarsStyles = makeStyles((theme) => ({
    tableHead: {
        background: '#4BBEBAE0',
    },
    container: {
        padding: '1em'
    },
    check: {
        color: "green"
    },
    clear: {
        color: "red"
    },
    table: {
        height: "10vh"
    },
    rented: {
        background: "#FFD700"
    },
    returned: {
        background: "#32CD32"
    }
}));