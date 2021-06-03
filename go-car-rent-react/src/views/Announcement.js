
import {Container, Typography} from "@material-ui/core";
import {useEffect, useState} from "react";

import {
    getAnnouncement,
} from "../actions/actionRepository";
import image from "../uploads/transit.png";
import {NavLink} from "react-router-dom";
import {useAnnouncementStyles} from "../style/AnnouncementStyles";

export default function Announcement(props) {

    const classes = useAnnouncementStyles();

    const [amount, setAmount] = useState();
    const [authorId, setAuthorId] = useState();
    const [carBrand, setCarBrand] = useState();
    const [carModel, setCarModel] = useState();
    const [currency, setCurrency] = useState();
    const [status, setStatus] = useState();
    const [timeUnit, setTimeUnit] = useState();
    const [title, setTitle] = useState();

    useEffect(()=>{
        getAnnouncement(props.match.params.id).then((response)=>{
            setAmount(response.data.amount);
            setAuthorId(response.data.authorId);
            setCarBrand(response.data.carBrand);
            setCarModel(response.data.carModel);
            setCurrency(response.data.currency);
            setStatus(response.data.status);
            setTimeUnit(response.data.timeUnit);
            setTitle(response.data.title);
        }).catch((error)=>alert(error));
    }, [])

    return (
        <div className={classes.root}>
            <Typography variant={'h4'} className={classes.header}>
                Announcement details
            </Typography>
            <Container className={classes.content}>
                <div className={classes.img}>
                    <img src={image} alt={"No-image"} className={classes.image}/>
                </div>
                <Container className={classes.data}>
                    <Typography variant={"h5"}>
                        {title}
                        <Typography variant={"body1"}>{props.date}</Typography>
                    </Typography>
                    <NavLink to={'/users/' + authorId + '/profile'} className={classes.navLink}>
                        <Typography>
                            Author
                        </Typography>
                    </NavLink>
                    <Typography variant={'h6'}>
                        {amount} {currency} / {timeUnit}
                    </Typography>
                    <Typography>
                        Car status: {status}<br/>
                        Car brand: {carBrand}<br/>
                        Car model: {carModel}<br/>
                    </Typography>
                </Container>
            </Container>
        </div>)
}