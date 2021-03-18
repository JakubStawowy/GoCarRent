import React, {Component} from 'react';
import './components.css';
import {AppBar, makeStyles, Typography, Button, Badge, Grid} from "@material-ui/core";
import {NavLink} from "react-router-dom";

import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import PersonIcon from '@material-ui/icons/Person';
import MessageIcon from '@material-ui/icons/Message';
import SettingsIcon from '@material-ui/icons/Settings';

const useStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        // width: '16vw',
        height: '100%',
        // position: 'fixed',
        position: 'relative',
        // right: 0,
        justifyContent: 'space-around',
        boxShadow: 'none',
        paddingRight: '3em'
    },
    button: {
        background: 'white',
        width: '100%',
        borderRadius: '0 30px 30px 0',
        color: '#4BBEBAE0',
        '&:hover': {
            color: 'white'
        }
    }
}));

export default function RightSidebar() {
    const classes = useStyles();
    return (
        <Grid item xs>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={"/user/profile"}>
                    <Button className={classes.button}>
                        <PersonIcon />
                        <Typography variant={"BUTTON"}>profile</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/messages"}>
                    <Button className={classes.button}>
                        <MessageIcon />
                        <Typography variant={"BUTTON"}>messages</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/user/cars"}>
                    <Button className={classes.button}>
                        <DirectionsCarIcon />
                        <Typography variant={"BUTTON"}>your cars</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/settings"}>
                    <Button className={classes.button}>
                        <SettingsIcon />
                        <Typography variant={"BUTTON"}>settings</Typography>
                    </Button>
                </NavLink>
            </AppBar>
        </Grid>
    );
};