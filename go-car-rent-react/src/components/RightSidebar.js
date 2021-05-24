import React, {Component, useEffect, useState} from 'react';
import './components.css';
import {AppBar, makeStyles, Typography, Button, Badge, Grid, Avatar} from "@material-ui/core";
import {NavLink} from "react-router-dom";

import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';
import PersonIcon from '@material-ui/icons/Person';
import MessageIcon from '@material-ui/icons/Message';
import SettingsIcon from '@material-ui/icons/Settings';

import userImage from '../uploads/user.png';

const useStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '100%',
        position: 'relative',
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
    },
    circlePlate: {
        display: "flex",
        justifyContent: "flex-end"
    },
    circle: {
        width: '10vw',
        height: '10vw',
    },
    img: {
        borderRadius: '50%',
        width: '100%',
        objectFit: 'cover'
    }
}));

export default function RightSidebar() {
    const classes = useStyles();
    return (
        <Grid item xs>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/users/' + localStorage.getItem('userId') + '/profile'}>
                    <div className={classes.circlePlate}>
                        <Avatar
                            src={userImage}
                            alt={''}
                            className={classes.circle}
                        />
                    </div>
                </NavLink>
                <NavLink to={'/users/' + localStorage.getItem('userId') + '/profile'}>
                    <Button className={classes.button}>
                        <Typography variant={"BUTTON"} size={'large'}>profile</Typography>
                        <PersonIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/users/{id}/messages"}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"}>messages</Typography>
                        <MessageIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/users/{id}/cars"}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"}>your cars</Typography>
                        <DirectionsCarIcon />
                    </Button>
                </NavLink>
                <NavLink to={"/settings"}>
                    <Button className={classes.button} size={'large'}>
                        <Typography variant={"BUTTON"}>settings</Typography>
                        <SettingsIcon />
                    </Button>
                </NavLink>
            </AppBar>
        </Grid>
    );
};