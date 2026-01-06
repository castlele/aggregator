package com.castlelecs.tg.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.castlelecs.tg.models.Channel

interface ChannelRepository : JpaRepository<Channel, Long>
