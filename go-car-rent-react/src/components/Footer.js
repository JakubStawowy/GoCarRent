import React from 'react';
import {makeStyles, Typography, Button, Grid, Box, Avatar} from "@material-ui/core";
import {useDispatch, useSelector} from "react-redux";
import MenuIcon from '@material-ui/icons/Menu';

const useStyles = makeStyles((theme) => ({

    root: {
        display: 'none',
        [theme.breakpoints.down('xs')]: {
            display: 'flex',
            justifyContent: 'flex-end'
        },
        position: 'fixed',
        bottom: 0,
        width: '100vw',
        height: '12vh',
        opacity: '90%',
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
    },
    button: {
        height: '100%'
    }
}));

export default function Footer(props) {
    const classes = useStyles();
    const dispatch = useDispatch();

    return (
        <Grid item xs className={classes.root}>
            <Button className={classes.button} onClick={()=>props.action()}>
                <MenuIcon fontSize={"large"}/>
            </Button>
        </Grid>
    );
};