import React, {useEffect, useState} from 'react';
import {Container, Fab, Grid, Input, Typography} from "@material-ui/core";
import {getUserDetails, saveUser} from "../actions/actionRepository";
import {useHistory} from "react-router";
import {useSettingsStyles} from "../style/SettingsStyles";

export default function Settings() {

    /*  Hooks   */
    const classes = useSettingsStyles();
    const history = useHistory();

    /*  User Data   */
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [phone, setPhone] = useState('');

    useEffect(() => {
        getUserDetails(localStorage.getItem('userId')).then((response) => {

            setName(response.data.name);
            setSurname(response.data.surname);
            setPhone(response.data.phone);

        }).catch((error) => {
            if(error.response.status === 403) {
                localStorage.clear();
                history.replace('/login');
            }
        })
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        password === confirmedPassword ?
        saveUser({
            name,
            surname,
            phone
        }).then(() => {
            alert("User saved succesfully");
            history.replace("/");
        }).catch((error) => {
            alert(error);
            if(error.response.status === 403) {
                localStorage.clear();
                history.replace('/');
            }
        })
            :
            alert("Passwords does not match")
    }

    return (
        <Container className={classes.container}>
            <Typography variant={'h4'} align={'center'}>
                User settings
            </Typography>
            <form className={classes.form} onSubmit={handleSubmit}>
                <Grid container className={`${classes.container} ${classes.gridContainer}`}>
                    <Grid item xs={6} className={classes.item}>
                        <Input
                            type={'text'}
                            className={classes.input}
                            placeholder={"name"}
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input
                            type={'text'}
                            className={classes.input}
                            placeholder={"surname"}
                            value={surname}
                            onChange={(e) => setSurname(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input
                            type={'password'}
                            className={classes.input}
                            placeholder={"password"}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input
                            type={'password'}
                            className={classes.input}
                            placeholder={"confirm password"}
                            value={confirmedPassword}
                            onChange={(e) => setConfirmedPassword(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input
                            type={'text'}
                            className={classes.input}
                            placeholder={"phone"}
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Input type={'file'} className={classes.input}/>
                    </Grid>
                    <Grid item xs={6} className={classes.item}>
                        <Fab variant={'extended'} className={classes.button} type={"submit"}>
                            Change
                        </Fab>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
}